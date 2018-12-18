import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {CreateWriteOffActDto} from "../dto/create-write-off-act.dto";
import {PlacementCreateWriteOffActDto} from "../dto/placement-create-write-off-act.dto";

@Component({
  selector: 'app-create-write-off-act-dialog',
  templateUrl: './create-write-off-act-dialog.component.html',
  styleUrls: ['./create-write-off-act-dialog.component.css']
})
export class CreateWriteOffActDialogComponent implements OnInit {


  constructor(private dialogRef: MatDialogRef<CreateWriteOffActDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit() {
  }

  closeModal() {
    this.dialogRef.close();
  }

  writeOffActSubmitted(createWriteOffActDto: CreateWriteOffActDto) {
    this.dialogRef.close(createWriteOffActDto);
  }

  placementWriteOffActSubmitted(placementCreateWriteOffActDto: PlacementCreateWriteOffActDto) {
    this.dialogRef.close(placementCreateWriteOffActDto);
    console.log(placementCreateWriteOffActDto);
  }

}
