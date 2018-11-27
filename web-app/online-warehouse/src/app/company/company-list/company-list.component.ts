import {Component, OnInit} from '@angular/core';
import {CompanyService} from "../service/company.service";
import {CompanyDto} from "../dto/company.dto";
import {BehaviorSubject, of} from "rxjs";
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {catchError, finalize} from "rxjs/operators";
import {PageEvent} from "@angular/material";
import {ActionTypeEnum} from "../dto/enum/action-type.enum";

@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {

  private actionType = ActionTypeEnum;

  private displayedColumns = ["id", "name", "sizeType", "actionType", "change", "changeStatus"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<CompanyDto>;

  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  private errors: any[];

  constructor(private companyService: CompanyService) {
  }

  ngOnInit() {
    this.updateCompaniesList();
  }

  disableCompany(companyId: number) {
    this.page = null;
    this.loadingSubject.next(true);
    this.companyService.setCompanyDisabled(companyId).subscribe(() => this.updateCompaniesList());
  }

  enableCompany(companyId: number) {
    this.page = null;
    this.loadingSubject.next(true);
    this.companyService.setCompanyEnabled(companyId).subscribe(() => this.updateCompaniesList());
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.updateCompaniesList();
  }

  updateCompaniesList(): void {
    this.loadingSubject.next(true);
    this.companyService.getAllCompanies(this.pageable).pipe(
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(page => {
        if (page instanceof Array) {
          this.errors = page as any[];
        } else {
          this.page = page;
        }
      });
  }

// CHART
  public pieChartLabels:string[] = ['Small', 'Average', 'Big'];
  public pieChartData:number[] = [300, 500, 100];
  public pieChartType:string = 'pie';

  // events
  public chartClicked(e:any):void {
    console.log(e);
  }

  public chartHovered(e:any):void {
    console.log(e);
  }
}
