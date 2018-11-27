import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-register-consignment-note-dialog',
  templateUrl: './register-consignment-note-dialog.component.html',
  styleUrls: ['./register-consignment-note-dialog.component.css']
})
export class RegisterConsignmentNoteDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<RegisterConsignmentNoteDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
  }

}
