import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: 'app-consignment-note-detail-dialog',
  templateUrl: './consignment-note-detail-dialog.component.html',
  styleUrls: ['./consignment-note-detail-dialog.component.css']
})
export class ConsignmentNoteDetailDialogComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ConsignmentNoteDetailDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
  }

}
