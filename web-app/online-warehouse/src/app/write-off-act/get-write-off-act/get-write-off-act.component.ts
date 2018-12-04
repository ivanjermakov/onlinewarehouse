import {Component, OnInit} from '@angular/core';
import {WriteOffActService} from "../service/write-off-act.service";
import {ActivatedRoute} from "@angular/router";
import {WriteOffActDto} from "../dto/write-off-act.dto";
import {BehaviorSubject, of} from "rxjs";
import {catchError, finalize} from "rxjs/operators";
import {WriteOffActGoodsDto} from "../dto/write-off-act-goods.dto";
import {WriteOffActTypeEnum} from "../dto/enum/write-off-act-type.enum";
import {PlacementTypeEnum} from "../../shared/enum/placement-type.enum";
import {WriteOffTypeEnum} from "../dto/enum/write-off-type.enum";

@Component({
  selector: 'app-get-write-off-act',
  templateUrl: './get-write-off-act.component.html',
  styleUrls: ['./get-write-off-act.component.css']
})
export class GetWriteOffActComponent implements OnInit {

  writeOffActId: number;
  writeOffAct: WriteOffActDto;
  displayedColumns = ["writeOffType", "amount", "name", "placementType", "measurementUnit", "cost", "weight", "labelling", "description"];
  private totalCost: number = 0;
  private totalWeight: number = 0;
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();

  private writeOffTypeEnum = WriteOffTypeEnum;
  private placementTypeEnum = PlacementTypeEnum;
  private writeOffActTypeEnum = WriteOffActTypeEnum;

  private errors: any[];

  constructor(
    private writeOffActService: WriteOffActService,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    if (this.idExist()) {
      this.getWriteOffAct();
    } else {
    }
  }

  idExist(): boolean {
    if (this.route.snapshot.paramMap.get('id') !== null) {
      this.writeOffActId = +this.route.snapshot.paramMap.get('id');
      return true;
    } else {
      return false;
    }
  }

  getWriteOffAct() {
    this.loadingSubject.next(true);
    this.writeOffActService.getWriteOffAct(this.writeOffActId).pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(page => {
        if (page instanceof Array) {
          this.errors = page as any[];
          console.log('test', this.errors)
        } else {
          this.writeOffAct = page;
          this.getTotal();
          console.log('test', this.writeOffAct)
        }
      });
  }

  private getTotal() {
    this.writeOffAct.writeOffActGoodsList.forEach((goods: WriteOffActGoodsDto) => {
      this.totalCost += goods.amount * goods.goods.cost;
      this.totalWeight += goods.amount * goods.goods.weight;
    })
  }

}
