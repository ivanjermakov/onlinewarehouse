import {Component, OnInit} from '@angular/core';
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {ConsignmentNoteService} from "../consignment-note.service";
import {ConsignmentNoteFilter} from "../dto/consignment-note-filter";
import {ActivatedRoute, Router} from "@angular/router";
import {ConsignmentNoteListDto} from "../dto/consignment-note-list-dto";

@Component({
  selector: 'app-consignment-note-list',
  templateUrl: './consignment-note-list.component.html',
  styleUrls: ['./consignment-note-list.component.css']
})
export class ConsignmentNoteListComponent implements OnInit {
  consignmentNotes: Page<ConsignmentNoteListDto> = new Page<ConsignmentNoteListDto>();
  pageable: Pageable = new Pageable(1, 10);
  consignmentNoteFilter: ConsignmentNoteFilter = new ConsignmentNoteFilter();

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getConsignmentNotes();
  }

  getConsignmentNotes(): void {
    // const id = Number(this.route.snapshot.paramMap.get('companyId'));
    const companyId = 2;
    this.consignmentNoteService.getConsignmentNotes(companyId, this.consignmentNoteFilter.toServerFilter(), this.pageable.toServerPageable())
      .subscribe((consignmentNotes) => {
        console.log(consignmentNotes);
        this.consignmentNotes = consignmentNotes;
      });
  }
}
