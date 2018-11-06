import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material";
import {CarrierListDto} from "../dto/carrier-list.dto";

@Component({
  selector: 'app-carrier-list-dialog',
  templateUrl: './carrier-list-dialog.component.html',
  styleUrls: ['./carrier-list-dialog.component.css']
})
export class CarrierListDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<CarrierListDialogComponent>) {
  }

  ngOnInit() {
  }

  selected(carrier: CarrierListDto) {
    this.dialogRef.close(carrier)
  }
}
