import { Component, OnInit } from '@angular/core';
import {ConsignmentNoteDto} from "../../dto/consignment-note-dto";
import {ConsignmentNoteService} from "../../consignment-note.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-consignment-note-detail',
  templateUrl: './consignment-note-detail.component.html',
  styleUrls: ['./consignment-note-detail.component.css']
})
export class ConsignmentNoteDetailComponent implements OnInit {
  consignmentNote: ConsignmentNoteDto = new ConsignmentNoteDto();
  displayedColumns: string[] = ['Name', 'Labelling', 'Measurement unit', 'Placement type',
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

  backToList() {
    this.router.navigateByUrl("app/consignment-notes");
  }
}
