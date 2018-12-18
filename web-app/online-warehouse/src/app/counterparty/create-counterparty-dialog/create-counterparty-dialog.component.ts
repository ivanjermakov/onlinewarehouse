import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-create-counterparty-dialog',
  templateUrl: './create-counterparty-dialog.component.html',
  styleUrls: ['./create-counterparty-dialog.component.css']
})
export class CreateCounterpartyDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<CreateCounterpartyDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    console.log(this.data);
  }

  ngOnInit() {
  }

  submitted() {
    this.dialogRef.close()
  }
}
