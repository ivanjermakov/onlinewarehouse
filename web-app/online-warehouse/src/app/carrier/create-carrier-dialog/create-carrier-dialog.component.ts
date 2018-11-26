import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-create-carrier-dialog',
  templateUrl: './create-carrier-dialog.component.html',
  styleUrls: ['./create-carrier-dialog.component.css']
})
export class CreateCarrierDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<CreateCarrierDialogComponent>) {
  }

  ngOnInit() {
  }

  submitted() {
    this.dialogRef.close()
  }
}
