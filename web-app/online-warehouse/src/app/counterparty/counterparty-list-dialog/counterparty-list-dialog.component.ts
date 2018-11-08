import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {CounterpartyDto} from "../dto/counterparty.dto";

@Component({
  selector: 'app-counterparty-list-dialog',
  templateUrl: './counterparty-list-dialog.component.html',
  styleUrls: ['./counterparty-list-dialog.component.css']
})
export class CounterpartyListDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<CounterpartyListDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
  }

  selected(counterparty: CounterpartyDto) {
    this.dialogRef.close(counterparty)
  }

  // Как уюзать
  // openModal(): void {
  //   const dialogRef = this.dialog.open(CounterpartyListDialogComponent, {
  //     disableClose: false,  //- стандартный конфиг
  //     autoFocus: true, //- тоже он
  //     data: {
  //       counterpartyType: CounterpartyTypeEnum.CONSIGNEE, // кого выводить (отправители/получатели)
  //       showCounterpartyTypeFilter: false, // показывать ли фильтр по отправителям/получателям
  //       addButton: true // показывать ли кнопку добавления контрагента (пока что не реализовано, просто кнопка, потом добавлю)
  //     }
  //   });
  //
  //   dialogRef.afterClosed().subscribe(
  //     data => {
  //       if (data) {
  //         this.addGoods(data); // тут твой метод
  //       }
  //     }
  //   );
  // }
}
