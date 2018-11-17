import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {CarrierListDto} from "../dto/carrier-list.dto";

@Component({
  selector: 'app-carrier-list-dialog',
  templateUrl: './carrier-list-dialog.component.html',
  styleUrls: ['./carrier-list-dialog.component.css']
})
export class CarrierListDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<CarrierListDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
  }

  selected(carrier: CarrierListDto) {
    this.dialogRef.close(carrier)
  }
}
