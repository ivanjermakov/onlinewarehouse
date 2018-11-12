import {Component, OnInit} from '@angular/core';
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {ConsignmentNoteService} from "../consignment-note.service";
import {ConsignmentNoteFilter} from "../dto/consignment-note-filter";
import {ConsignmentNoteListDto} from "../dto/consignment-note-list-dto";
import {BehaviorSubject} from "rxjs/index";
import {PageEvent} from "@angular/material";
import {Router} from "@angular/router";

@Component({
  selector: 'app-consignment-note-list',
  templateUrl: './consignment-note-list.component.html',
  styleUrls: ['./consignment-note-list.component.css']
})
export class ConsignmentNoteListComponent implements OnInit {

  private displayedColumns = ["Number", "Company name", "Registration date", "Consignment Note Type", "Consignment Note Status"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];
  private errors: any[];

  consignmentNotes: Page<ConsignmentNoteListDto> = new Page<ConsignmentNoteListDto>();
  consignmentNoteFilter: ConsignmentNoteFilter = new ConsignmentNoteFilter();

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getConsignmentNotes();
  }

  getConsignmentNotes(): void {
    // const id = Number(this.route.snapshot.paramMap.get('companyId'));
    const companyId = 2;
    this.consignmentNoteService.getConsignmentNotes(companyId, this.consignmentNoteFilter.toServerFilter(), this.pageable.toServerPageable())
      .subscribe((consignmentNotes) => this.consignmentNotes = consignmentNotes,
        error => this.errors = error);
  }

  onRowClicked(row) {
    this.router.navigateByUrl("app/consignment-notes/" + row.id);
  }

  pageChanged(event: PageEvent) {
    this.consignmentNotes = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.getConsignmentNotes();
  }
}
