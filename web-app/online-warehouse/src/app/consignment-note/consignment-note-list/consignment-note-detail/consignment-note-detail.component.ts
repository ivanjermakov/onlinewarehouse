import {Component, Input, OnInit} from '@angular/core';
import {ConsignmentNoteDto} from "../../dto/consignment-note-dto";
import {ConsignmentNoteService} from "../../consignment-note.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatDialog} from "@angular/material";
import {CreateWriteOffActDialogComponent} from "../../../write-off-act/create-write-off-act-dialog/create-write-off-act-dialog.component";
import {CommodityLotService} from "../../../commodity-lot/service/commodity-lot.service";

@Component({
  selector: 'app-consignment-note-detail',
  templateUrl: './consignment-note-detail.component.html',
  styleUrls: ['./consignment-note-detail.component.css']
})
export class ConsignmentNoteDetailComponent implements OnInit {

  @Input() showWriteOffButtons: boolean = false;
  @Input() inputConsignmentNote: ConsignmentNoteDto;

  consignmentNote: ConsignmentNoteDto;
  displayedColumns: string[] = ['Name', 'Labelling', 'Measurement unit', 'Placement type',
    'Weight', 'Cost', 'Description', 'Amount'];

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private router: Router,
              private route: ActivatedRoute,
              private dialog: MatDialog,
              private commodityLotService: CommodityLotService) {
  }

  ngOnInit(): void {
    if (!this.inputConsignmentNote) {
      this.getConsignmentNote();
    } else {
      this.consignmentNote = this.inputConsignmentNote;
    }
  }

  getConsignmentNote(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!Number.isNaN(id) && id != 0) {
      this.consignmentNoteService.getConsignmentNote(id)
        .subscribe((consignmentNote) => this.consignmentNote = consignmentNote);
    }
  }

  backToList() {
    this.router.navigateByUrl("app/consignment-notes");
  }

  submitWithAct() {
    this.consignmentNoteService.setConsignmentNoteBeingProcessed(this.consignmentNote.id).subscribe();
    const dialogRef = this.dialog.open(CreateWriteOffActDialogComponent, {
      disableClose: false,
      autoFocus: true,
      data: {
        emitWhenSubmit: true
      }
    });

    dialogRef.afterClosed().subscribe((createWriteOffActDto) => {
        if (createWriteOffActDto) {
          console.log('test');
          this.commodityLotService
            .saveCommodityLotFromConsignmentNoteAndWriteOffAct(this.consignmentNote, createWriteOffActDto)
            .subscribe();
        }
      }
    );
  }

  submitWithoutAct() {
    this.consignmentNoteService.setConsignmentNoteProcessed(this.consignmentNote.id).subscribe();
    this.commodityLotService.saveCommodityLotFromConsignmentNote(this.consignmentNote).subscribe();
  }
}
