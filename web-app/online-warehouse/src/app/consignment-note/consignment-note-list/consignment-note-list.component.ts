import {Component, OnInit} from '@angular/core';
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {ConsignmentNoteService} from "../consignment-note.service";
import {ConsignmentNoteFilter} from "../dto/consignment-note-filter";
import {ConsignmentNoteListDto} from "../dto/consignment-note-list-dto";
import {BehaviorSubject} from "rxjs/index";
import {PageEvent} from "@angular/material";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {debounceTime, distinctUntilChanged, tap} from "rxjs/operators";

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

  private consignmentNotes: Page<ConsignmentNoteListDto>;
  private consignmentNoteFilterForm: FormGroup;
  private consignmentNoteFilter: ConsignmentNoteFilter = new ConsignmentNoteFilter();

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private router: Router,
              private fb: FormBuilder) {
    this.consignmentNoteFilterForm = fb.group({
      "consignmentNoteType": [''],
      "consignmentNoteStatus": [''],
      "from": [''],
      "to": ['']
    });
  }


  ngOnInit(): void {
    this.getConsignmentNotes();
    this.consignmentNoteFilterForm.valueChanges.pipe(debounceTime(250),
      distinctUntilChanged(),
      tap(() => {
        this.consignmentNotes = null;
        this.pageable.page = 0;
        let value = this.consignmentNoteFilterForm.value;
        Object.assign(this.consignmentNoteFilter, value);
        this.getConsignmentNotes();
      })
    ).subscribe();
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
