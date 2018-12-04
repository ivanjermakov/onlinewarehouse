import {Component, Input, OnInit} from '@angular/core';
import {ConsignmentNoteDto} from "../../dto/consignment-note-dto";
import {ConsignmentNoteService} from "../../consignment-note.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog} from "@angular/material";
import {CreateWriteOffActDialogComponent} from "../../../write-off-act/create-write-off-act-dialog/create-write-off-act-dialog.component";
import {CommodityLotService} from "../../../commodity-lot/service/commodity-lot.service";
import {ConsignmentNoteType} from "../../dto/enum/consignment-note-type.enum";
import {ConsignmentNoteStatus} from "../../dto/enum/consignment-note-status.enum";
import {WriteOffActService} from "../../../write-off-act/service/write-off-act.service";
import {GoodsDto} from "../../../shared/goods/dto/goods.dto";
import {ConsignmentNoteGoodsDto} from "../../dto/consignment-note-goods-dto";
import {CounterpartyTypeEnum} from "../../../counterparty/dto/enum/counterparty-type.enum";
import {CarrierTypeEnum} from "../../../carrier/dto/enum/carrier-type.enum";
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'app-consignment-note-detail',
  templateUrl: './consignment-note-detail.component.html',
  styleUrls: ['./consignment-note-detail.component.css']
})
export class ConsignmentNoteDetailComponent implements OnInit {

  @Input() showWriteOffButtons: boolean = false;
  @Input() inputConsignmentNote: ConsignmentNoteDto;

  private totalCost: number = 0;
  private totalWeight: number = 0;

  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();

  private counterpartyType = CounterpartyTypeEnum;
  private carrierTypeEnum = CarrierTypeEnum;
  private status = ConsignmentNoteStatus;
  private type = ConsignmentNoteType;

  private consignmentNote: ConsignmentNoteDto;
  private displayedColumns: string[] = ['Name', 'Labelling', 'Measurement unit', 'Placement type',
    'Weight', 'Cost', 'Description', 'Amount'];

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private router: Router,
              private route: ActivatedRoute,
              private dialog: MatDialog,
              private commodityLotService: CommodityLotService,
              private writeOffActService: WriteOffActService) {
  }

  ngOnInit(): void {
    if (!this.inputConsignmentNote) {
      this.getConsignmentNote();
    } else {
      this.consignmentNote = this.inputConsignmentNote;
    }
  }

  getConsignmentNote(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!Number.isNaN(id) && id != 0) {
      this.loadingSubject.next(true);
      this.consignmentNoteService.getConsignmentNote(id)
        .subscribe((consignmentNote) => {
          this.consignmentNote = consignmentNote;
          this.getTotal();
          this.loadingSubject.next(false);
        });
    }
  }

  update() {
    console.log(11);
    this.router.navigateByUrl("app/register-consignment-note/" + this.consignmentNote.id);
  }

  backToList() {
    this.router.navigateByUrl("app/consignment-notes");
  }

  submitWithAct() {
    this.consignmentNoteService.setConsignmentNoteBeingProcessed(this.consignmentNote.id).subscribe();
    const dialogRef = this.dialog.open(CreateWriteOffActDialogComponent, {
      disableClose: false,
      autoFocus: true,
      data: {
        inputGoods: this.getGoodsDtoArr(),
        emitWhenSubmit: true
      }
    });

    dialogRef.afterClosed().subscribe((createWriteOffActDto) => {
        if (createWriteOffActDto) {
          console.log('test');
          this.writeOffActService
            .saveWriteOffActAndCommodityLot(createWriteOffActDto,
              this.commodityLotService.getCommodityLotFromConsignmentNoteAndWriteOffAct(this.consignmentNote, createWriteOffActDto))
            .subscribe((pair) => {
              console.log('writeOffActId:' + pair.value1, 'commodityLotId: ' + pair.value2)
            });
        }
      }
    );
  }

  submitWithoutAct() {
    this.consignmentNoteService.setConsignmentNoteProcessed(this.consignmentNote.id).subscribe();
    this.commodityLotService.saveCommodityLotFromConsignmentNote(this.consignmentNote).subscribe();
  }

  private getGoodsDtoArr(): GoodsDto[] {
    return this.consignmentNote.consignmentNoteGoodsList.map((goods) => {
      return goods.goods;
    })
  }

  private getTotal() {
    this.consignmentNote.consignmentNoteGoodsList.forEach((goods: ConsignmentNoteGoodsDto) => {
      this.totalCost += goods.amount * goods.goods.cost;
      this.totalWeight += goods.amount * goods.goods.weight;
    })
  }
}
