import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-create-goods-dialog',
  templateUrl: './create-goods-dialog.component.html',
  styleUrls: ['./create-goods-dialog.component.css']
})
export class CreateGoodsDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<CreateGoodsDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log(this.data);
  }

  ngOnInit() {
  }

  submitted() {
    this.dialogRef.close()
  }
}
