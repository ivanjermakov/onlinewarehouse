import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {finalize} from "rxjs/operators";
import {PlacementDto} from "../dto/placement.dto";
import {WarehouseService} from "../service/warehouse.service";
import * as Highcharts from 'highcharts';

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


  private error: any;

  constructor(private warehouseService: WarehouseService,
              private router: Router,
              private route: ActivatedRoute) {
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

}
