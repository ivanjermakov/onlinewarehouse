import { Component, OnInit } from '@angular/core';
import {ConsignmentNoteDto} from "../../dto/consignment-note-dto";
import {ConsignmentNoteService} from "../../consignment-note.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ConsignmentNoteType} from "../../dto/enum/consignment-note-type.enum";
import {ConsignmentNoteStatus} from "../../dto/enum/consignment-note-status.enum";

@Component({
  selector: 'app-consignment-note-detail',
  templateUrl: './consignment-note-detail.component.html',
  styleUrls: ['./consignment-note-detail.component.css']
})
export class ConsignmentNoteDetailComponent implements OnInit {
  private status = ConsignmentNoteStatus;
  private type = ConsignmentNoteType;
  private consignmentNote: ConsignmentNoteDto;
  private displayedColumns: string[] = ['Name', 'Labelling', 'Measurement unit', 'Placement type',
    'Weight', 'Cost', 'Description', 'Amount'];

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getConsignmentNote();
  }

  getConsignmentNote(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!Number.isNaN(id) && id != 0) {
      this.consignmentNoteService.getConsignmentNote(id)
        .subscribe((consignmentNote) => this.consignmentNote = consignmentNote);
    }
  }

  update() {
    this.router.navigateByUrl("app/register-consignment-note/" + this.consignmentNote.id);
  }

  backToList() {
    this.router.navigateByUrl("app/consignment-notes");
  }
}
