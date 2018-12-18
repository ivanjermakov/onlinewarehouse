import {Component, OnInit} from '@angular/core';
import {WriteOffActListDto} from "../dto/write-off-act-list.dto";
import {WriteOffActService} from "../service/write-off-act.service";
import {WriteOffActFilter} from "../dto/write-off-act.filter";
import {Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";
import {Page} from "../../shared/pagination/page";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Pageable} from "../../shared/pagination/pageable";
import {debounceTime, distinctUntilChanged, finalize, tap} from "rxjs/operators";
import {PageEvent} from "@angular/material";
import {WriteOffActTypeEnum} from "../dto/enum/write-off-act-type.enum";
import {AuthenticationService} from "../../auth/_services";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-write-off-act-list',
  templateUrl: './write-off-act-list.component.html',
  styleUrls: ['./write-off-act-list.component.css']
})
export class WriteOffActListComponent implements OnInit {

  writeOffActFilterForm: FormGroup;
  private displayedColumns = ["id", "creation", "creatorId", "responsiblePerson", "totalAmount", "writeOffActType"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<WriteOffActListDto>;
  private filter: WriteOffActFilter = new WriteOffActFilter();

  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  private minDate: Date = new Date(2000, 0, 1);
  private today: Date = new Date();

  private writeOffActType = WriteOffActTypeEnum;

  constructor(private writeOffActService: WriteOffActService,
              private router: Router,
              private fb: FormBuilder,
              private auth: AuthenticationService,
              private errorToast: RequestErrorToastHandlerService) {
    this.writeOffActFilterForm = fb.group({
      "writeOffActType": [''],
      "from": [''],
      "to": ['']
    });
  }

  ngOnInit() {
    this.loadWriteOffActs();
    this.writeOffActFilterForm.valueChanges.pipe(debounceTime(250),
      distinctUntilChanged(),
      tap(() => {
        this.page = null;
        this.pageable.page = 0;
        let value = this.writeOffActFilterForm.value;
        Object.assign(this.filter, value);
        this.loadWriteOffActs();
      })
    )
      .subscribe();
  }

  onRowClicked(writeOffActListDto: WriteOffActListDto) {
    this.router.navigate(['app/write-off-act/' + writeOffActListDto.id]);
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.loadWriteOffActs();

  }

  getWriteOffActTypes(): Array<string> {
    return Object.keys(WriteOffActTypeEnum);
  }

  private loadWriteOffActs() {
    this.loadingSubject.next(true);
    this.writeOffActService.getWriteOffActs(this.filter, this.pageable)
      .pipe(finalize(() => this.loadingSubject.next(false)))
      .subscribe((page) => {
          this.page = page;
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }
}
