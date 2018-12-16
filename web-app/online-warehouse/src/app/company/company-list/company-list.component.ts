import {Component, OnInit} from '@angular/core';
import {CompanyService} from "../service/company.service";
import {CompanyDto} from "../dto/company.dto";
import {BehaviorSubject} from "rxjs";
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {finalize} from "rxjs/operators";
import {PageEvent} from "@angular/material";
import {ActionTypeEnum} from "../dto/enum/action-type.enum";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {

  private actionType = ActionTypeEnum;

  private displayedColumns = ["name", "sizeType", "actionType", "change", "changeStatus"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<CompanyDto>;

  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  constructor(private companyService: CompanyService,
              private errorToast: RequestErrorToastHandlerService) {
  }

  ngOnInit() {
    this.updateCompaniesList();
  }

  disableCompany(companyId: number) {
    this.page = null;
    this.loadingSubject.next(true);
    this.companyService.setCompanyDisabled(companyId)
      .subscribe(() => this.updateCompaniesList(),
        (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

  enableCompany(companyId: number) {
    this.page = null;
    this.loadingSubject.next(true);
    this.companyService.setCompanyEnabled(companyId)
      .subscribe(() => this.updateCompaniesList(),
        (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.updateCompaniesList();
  }

  updateCompaniesList(): void {
    this.loadingSubject.next(true);
    this.companyService.getAllCompanies(this.pageable)
      .pipe(finalize(() => this.loadingSubject.next(false)))
      .subscribe((page) => {
          this.page = page;
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }
}
