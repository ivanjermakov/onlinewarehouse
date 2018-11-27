import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-distribute-goods-warehouse-dialog',
  templateUrl: './distribute-goods-warehouse-dialog.component.html',
  styleUrls: ['./distribute-goods-warehouse-dialog.component.css']
})
export class DistributeGoodsWarehouseDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<DistributeGoodsWarehouseDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
  }

  closeModal() {
    this.dialogRef.close(true);
  }

}
