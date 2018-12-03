import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material";
import {FormControl, Validators} from "@angular/forms";
import {DriverDto} from "../driver.dto";

@Component({
  selector: 'app-create-driver-dialog',
  templateUrl: './create-driver-dialog.component.html',
  styleUrls: ['./create-driver-dialog.component.css']
})
export class CreateDriverDialogComponent implements OnInit {

  private driverControl: FormControl;

  constructor(private dialogRef: MatDialogRef<CreateDriverDialogComponent>) {
  }

  ngOnInit() {
    this.driverControl = new FormControl('', [Validators.required, Validators.maxLength(70)]);
  }

  submitted() {
    let driverDto = new DriverDto();
    driverDto.info = this.driverControl.value;
    driverDto.id = null;
    this.dialogRef.close(driverDto)
  }
}
