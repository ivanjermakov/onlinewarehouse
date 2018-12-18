import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {DriverDto} from "../driver.dto";

@Component({
  selector: 'app-driver-list-dialog',
  templateUrl: './driver-list-dialog.component.html',
  styleUrls: ['./driver-list-dialog.component.css']
})
export class DriverListDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<DriverListDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
  }

  selected(driver: DriverDto) {
    this.dialogRef.close(driver)
  }
}
