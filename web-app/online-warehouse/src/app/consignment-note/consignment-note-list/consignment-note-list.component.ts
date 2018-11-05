import { Component, OnInit } from '@angular/core';
import {Page} from "../../shared/pagination/page";
import {ConsignmentNoteDto} from "../dto/consignment-note-dto";
import {Pageable} from "../../shared/pagination/pageable";
import {Router} from "@angular/router";
import {ConsignmentNoteService} from "../consignment-note.service";
import {ConsignmentNoteFilter} from "../dto/consignment-note-filter";

@Component({
  selector: 'app-consignment-note-list',
  templateUrl: './consignment-note-list.component.html',
  styleUrls: ['./consignment-note-list.component.css']
})
export class ConsignmentNoteListComponent implements OnInit {
  consignmentNotes: Page<ConsignmentNoteDto> = new Page<ConsignmentNoteDto>();
  pageable: Pageable = new Pageable(1, 10);
  consignmentNoteFilter: ConsignmentNoteFilter = new ConsignmentNoteFilter();

  constructor(
    private consignmentNoteService: ConsignmentNoteService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
    this.getContacts();
  }

  getContacts(): void {
    this.consignmentNoteService.getConsignmentNotes(this.consignmentNoteFilter.toServerFilter(), this.pageable.toServerPageable())
      .subscribe((consignmentNotes) => {
        this.consignmentNotes = consignmentNotes;
      });
  }
}
