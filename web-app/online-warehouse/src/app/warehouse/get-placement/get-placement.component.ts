import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {finalize} from "rxjs/operators";
import {PlacementDto} from "../dto/placement.dto";
import {WarehouseService} from "../service/warehouse.service";
import * as Highcharts from 'highcharts';
import {CreateWriteOffActDialogComponent} from "../../write-off-act/create-write-off-act-dialog/create-write-off-act-dialog.component";
import {MatDialog} from "@angular/material";
import {PlacementCreateWriteOffActDto} from "../../write-off-act/dto/placement-create-write-off-act.dto";
import {PlacementGoodsDto} from "../dto/placement-goods.dto";
import {PlacementWriteOffActGoodsDto} from "../../write-off-act/dto/placement-write-off-act-goods.dto";
import {PlacementTypeEnum} from "../../shared/enum/placement-type.enum";

@Component({
  selector: 'app-get-placement',
  templateUrl: './get-placement.component.html',
  styleUrls: ['./get-placement.component.css']
})
export class GetPlacementComponent implements OnInit {
  Highcharts = Highcharts;
  chartOptionsLoadPercentage = {
    chart: {
      plotBackgroundColor: null,
      plotBorderWidth: null,
      plotShadow: false,
      type: 'pie'
    },
    title: {
      text: 'Placement Load'
    },
    plotOptions: {
      pie: {
        allowPointSelect: true,
        cursor: 'pointer',
        dataLabels: {
          enabled: true,
          format: '{point.name}: {point.percentage:.1f} %',
          distance: -50,
          filter: {
            property: 'percentage',
            operator: '>',
            value: 4
          }
        }
      }
    },
    tooltip: {
      pointFormat: '{point.name}: {point.y}'
    },
    series: [{
      name: 'Placement part',
      colorByPoint: true,
      data: []
    }]
  };

  chartOptionsLoadDate = {
    chart: {
      type: 'spline'
    },
    title: {
      text: 'Placement Load'
    },
    xAxis: {
      type: 'datetime',
      dateTimeLabelFormats: { // don't display the dummy year
        month: '%e. %b',
        year: '%b'
      },
      title: {
        text: 'Date'
      }
    },
    yAxis: {
      title: {
        text: 'Amount'
      },
      min: 0
    },
    tooltip: {
      headerFormat: '',
      pointFormat: '{point.x:%e. %b}: {point.y} left'
    },

    plotOptions: {
      spline: {
        marker: {
          enabled: true
        }
      }
    },

    colors: ['#6CF'],

    // Define the data points. All series have a dummy year
    // of 1970/71 in order to be compared on the same x axis. Note
    // that in JavaScript, months start at 0 for January, 1 for February etc.
    series: [{
      name: "Placement",
      data: []
    }]
  };

  displayedColumns = ["amount", "name", "storageTimeDays", "expirationDate", "cost", "weight", "labelling", "description"];

  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private placement: PlacementDto;

  placementType = PlacementTypeEnum;

  private error: any;

  constructor(private warehouseService: WarehouseService,
              private router: Router,
              private route: ActivatedRoute,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.getPlacement();
  }

  calculateCount() {
    let placementLoad: number = 0;
    this.placement.placementGoodsList.forEach((placementGoods) => {
      placementLoad += placementGoods.amount;
    });

    this.chartOptionsLoadPercentage.series[0].data.push({
      name: 'Free',
      y: this.placement.size - placementLoad,
    }, {
      name: 'Employed',
      y: placementLoad

    });

    let placementLoadDynamic: any[] = [[Date.now(), placementLoad]];
    this.placement.placementGoodsList.forEach((placementGoods) => {
      placementLoadDynamic.push([new Date(placementGoods.expirationDate).getTime(), placementLoad]);
      placementLoadDynamic.push([new Date(placementGoods.expirationDate).getTime(), placementLoad -= placementGoods.amount])
    });
    placementLoadDynamic.sort((a, b) => {
      return a[0] - b[0]
    });
    console.log(placementLoadDynamic);
    placementLoadDynamic.forEach((arr) => {
      this.chartOptionsLoadDate.series[0].data.push(arr)
    });
  }

  getPlacement(): void {
    const warehouseId = Number(this.route.snapshot.paramMap.get('warehouseId'));
    const placementId = Number(this.route.snapshot.paramMap.get('placementId'));
    if (!Number.isNaN(warehouseId) && warehouseId != 0 && !Number.isNaN(placementId) && placementId != 0) {
      this.loadingSubject.next(true);
      this.warehouseService.getPlacement(warehouseId, placementId)
        .pipe(
          finalize(() => this.loadingSubject.next(false))
        )
        .subscribe(placement => {
            this.placement = placement;
            this.calculateCount();
          }, (err: any) => {
            this.error = err;
          }
        );
    }
  }

  createWriteOffAct() {
    const dialogRef = this.dialog.open(CreateWriteOffActDialogComponent, {
      disableClose: false,
      autoFocus: true,
      data: {
        emitWhenSubmit: true,
        placementGoods: this.placement.placementGoodsList
      }
    });

    dialogRef.afterClosed().subscribe((placementCreateWriteOffActDto: PlacementCreateWriteOffActDto) => {
        console.info('Write-off case dialog closed');
        if (placementCreateWriteOffActDto) {
          console.info('Write-off case dialog emit data closed');
          console.info(placementCreateWriteOffActDto);

          let noDuplicates: boolean = true;
          let checkDuplicatesArr = [];
          placementCreateWriteOffActDto.placementGoodsDtoList.forEach((placementWriteOffActGoodsDto: PlacementWriteOffActGoodsDto) => {
            checkDuplicatesArr.push(placementWriteOffActGoodsDto.placementGoods.id);
          });
          checkDuplicatesArr.sort();
          for (let i = 0; i < checkDuplicatesArr.length - 1; i++) {
            if (checkDuplicatesArr[i + 1] === checkDuplicatesArr[i]) {
              noDuplicates = false;
              break;
            }
          }

          if (noDuplicates) {
            console.info('Write-off case correct. Start save');
            this.warehouseService
              .createPlacementWriteOffAct(this.placement.warehouseId, this.placement.id, placementCreateWriteOffActDto)
              .subscribe((data) => {
                console.info('Write-off case saved', data);
              });
          } else {
            console.log('Error, duplicates in write-off case');
          }
        }
      }
    );
  }


  private comparePlacementGoodsDto(dto1: PlacementGoodsDto, dto2: PlacementGoodsDto): boolean {
    return dto1.id === dto2.id &&
      dto1.goods.id === dto2.goods.id &&
      dto1.counterpartyId === dto2.counterpartyId &&
      dto1.storageTimeDays === dto2.storageTimeDays &&
      dto1.expirationDate.getTime() === dto2.expirationDate.getTime();
  }
}
