import {Component, OnInit} from '@angular/core';
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {ConsignmentNoteService} from "../consignment-note.service";
import {ConsignmentNoteListDto} from "../dto/consignment-note-list-dto";
import {BehaviorSubject} from "rxjs/index";
import {MatDialog, PageEvent} from "@angular/material";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup} from "@angular/forms";
import {debounceTime, distinctUntilChanged, finalize, tap} from "rxjs/operators";
import {ConsignmentNoteStatus} from "../dto/enum/consignment-note-status.enum";
import {ConsignmentNoteType} from "../dto/enum/consignment-note-type.enum";
import {ConsignmentNoteDetailDialogComponent} from "./consignment-note-detail-dialog/consignment-note-detail-dialog.component";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-consignment-note-list',
  templateUrl: './consignment-note-list.component.html',
  styleUrls: ['./consignment-note-list.component.css']
})
export class ConsignmentNoteListComponent implements OnInit {

  private cnStatus = ConsignmentNoteStatus;
  private cnType = ConsignmentNoteType;
  private displayedColumns = ["number", "registration date", "consignment Note Type", "consignment Note Status"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];
  private active = true;

  private minDate: Date = new Date(2000, 0, 1);
  private today: Date = new Date();

  private consignmentNotes: Page<ConsignmentNoteListDto>;
  private consignmentNoteFilterForm: FormGroup;

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private router: Router,
              private fb: FormBuilder,
              private dialog: MatDialog,
              private errorToast: RequestErrorToastHandlerService) {
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
      this.active = false;
    }
    this.getConsignmentNotes();
    this.consignmentNoteFilterForm.valueChanges.pipe(debounceTime(500),
      distinctUntilChanged(),
      tap(() => {
        this.consignmentNotes = null;
        this.pageable.page = 0;
        this.getConsignmentNotes();
      })
    ).subscribe();
  }

  getConsignmentNotes(): void {
    this.loadingSubject.next(true);
    this.consignmentNoteService.getConsignmentNotes(this.consignmentNoteFilterForm.value, this.pageable)
      .pipe(finalize(() => this.loadingSubject.next(false)))
      .subscribe((consignmentNotes) => {
          this.consignmentNotes = consignmentNotes
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

  onRowClicked(row: ConsignmentNoteListDto) {
    if (!this.active) {
      this.openModal(row);
    } else {
      this.router.navigateByUrl("app/consignment-notes/" + row.id);
    }
  }


  getTypes(): Array<string> {
    return Object.keys(ConsignmentNoteType);
  }

  getStatuses(): Array<string> {
    return Object.keys(ConsignmentNoteStatus);
  }

  openModal(row: ConsignmentNoteListDto): void {

    this.consignmentNoteService.getConsignmentNote(row.id).subscribe((consignmentNoteDto) => {
        let dialogRef = this.dialog.open(ConsignmentNoteDetailDialogComponent, {
          disableClose: false,
          autoFocus: true,
          data: {
            showWriteOffButtons: true,
            consignmentNoteDto: consignmentNoteDto
          }
        });

        dialogRef.afterClosed().subscribe(
          data => {
            if (data) {
              this.getConsignmentNotes();
            }
          }
        );
      }, (err: any) => {
        this.errorToast.handleError(err);
      }
    );
  }

  pageChanged(event: PageEvent) {
    this.consignmentNotes = null;
    console.log(event);
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.getConsignmentNotes();
  }
}
