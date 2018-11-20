import {Component, OnInit} from '@angular/core';
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {ConsignmentNoteService} from "../consignment-note.service";
import {ConsignmentNoteListDto} from "../dto/consignment-note-list-dto";
import {BehaviorSubject} from "rxjs/index";
import {MatDialog, PageEvent} from "@angular/material";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {debounceTime, distinctUntilChanged, tap} from "rxjs/operators";
import {ConsignmentNoteDetailDialogComponent} from "./consignment-note-detail-dialog/consignment-note-detail-dialog.component";

@Component({
  selector: 'app-consignment-note-list',
  templateUrl: './consignment-note-list.component.html',
  styleUrls: ['./consignment-note-list.component.css']
})
export class ConsignmentNoteListComponent implements OnInit {

  private displayedColumns = ["number", "company name", "registration date", "consignment Note Type", "consignment Note Status"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];
  private errors: any[];
  private disabled = true;

  private consignmentNotes: Page<ConsignmentNoteListDto>;
  private consignmentNoteFilterForm: FormGroup;

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private router: Router,
              private fb: FormBuilder,
              private dialog: MatDialog) {
    this.consignmentNoteFilterForm = fb.group({
      "consignmentNoteType": [''],
      "consignmentNoteStatus": [''],
      "from": [''],
      "to": ['']
    });
  }


  ngOnInit(): void {
    if (this.router.url === '/app/registered-consignment-notes') {
      this.consignmentNoteFilterForm.patchValue({'consignmentNoteStatus': 'NOT_PROCESSED'});
      this.displayedColumns.push('consignment Process');
      this.disabled = false;
    }
    this.getConsignmentNotes();
    this.consignmentNoteFilterForm.valueChanges.pipe(debounceTime(250),
      distinctUntilChanged(),
      tap(() => {
        this.consignmentNotes = null;
        this.pageable.page = 0;
        this.getConsignmentNotes();
      })
    ).subscribe();
  }


  getConsignmentNotes(): void {
    this.consignmentNoteService.getConsignmentNotes(this.consignmentNoteFilterForm.value, this.pageable.toServerPageable())
      .subscribe((consignmentNotes) =>
          this.consignmentNotes = consignmentNotes,
        error => this.errors = error);
  }

  onRowClicked(row: ConsignmentNoteListDto) {
    if (!this.disabled) {
      this.openModal(row);
    } else {
      this.router.navigateByUrl("app/consignment-notes/" + row.id);
    }
  }

  openModal(row: ConsignmentNoteListDto): void {
    this.consignmentNoteService.getConsignmentNote(row.id).subscribe((consignmentNoteDto) => {
      const dialogRef = this.dialog.open(ConsignmentNoteDetailDialogComponent, {
        disableClose: false,
        autoFocus: true,
        data: {
          showWriteOffButtons: true,
          consignmentNoteDto: consignmentNoteDto
        }
      });
    });
  }

  pageChanged(event: PageEvent) {
    this.consignmentNotes = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.getConsignmentNotes();
  }
}
