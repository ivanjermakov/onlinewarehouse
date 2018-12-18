import {Component, Inject, OnInit} from '@angular/core';
import {GoodsDto} from "../dto/goods.dto";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-goods-list-dialog',
  templateUrl: './goods-list-dialog.component.html',
  styleUrls: ['./goods-list-dialog.component.css']
})
export class GoodsListDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<GoodsListDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
  }

  selected(goods: GoodsDto) {
    this.dialogRef.close(goods)
  }

}
