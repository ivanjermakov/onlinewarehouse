import { Component, OnInit } from '@angular/core';
import {ConsignmentNoteDto} from "../../dto/consignment-note-dto";
import {ConsignmentNoteService} from "../../consignment-note.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-consignment-note-detail',
  templateUrl: './consignment-note-detail.component.html',
  styleUrls: ['./consignment-note-detail.component.css']
})
export class ConsignmentNoteDetailComponent implements OnInit {
  consignmentNote: ConsignmentNoteDto = new ConsignmentNoteDto();

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getConsignmentNote();
  }

  getConsignmentNote(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    // const companyId = Number(this.route.snapshot.paramMap.get('companyId'));
    const companyId = 2;
    if (!Number.isNaN(id) && id != 0) {
      this.consignmentNoteService.getConsignmentNote(companyId, id)
        .subscribe((consignmentNote) => {
          console.log(consignmentNote);
          this.consignmentNote = consignmentNote;
        });
    }
  }

}
