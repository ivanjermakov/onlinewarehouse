import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BehaviorSubject, of} from "rxjs/index";
import {Pageable} from "../../../shared/pagination/pageable";
import {Page} from "../../../shared/pagination/page";
import {MatDialog, PageEvent} from "@angular/material";
import {DriverDto} from "../driver.dto";
import {DriverService} from "../driver.service";
import {catchError, finalize} from "rxjs/internal/operators";
import {CreateDriverDialogComponent} from "../create-driver-dialog/create-driver-dialog.component";

@Component({
  selector: 'app-driver-list',
  templateUrl: './driver-list.component.html',
  styleUrls: ['./driver-list.component.css']
})
export class DriverListComponent implements OnInit {

  @Output() driverSelected: EventEmitter<DriverDto> = new EventEmitter();
  @Input() carrierId: number;

  private displayedColumns = ["info"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<DriverDto>;
  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];
  private errors: any[];
  private error: any;

  constructor(private driverService: DriverService,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    this.loadDrivers();
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.loadDrivers();
  }

  onRowClicked(row) {
    this.driverSelected.emit(row);
  }

  loadDrivers() {
    this.loadingSubject.next(true);
    this.driverService.getDrivers(this.carrierId, this.pageable).pipe(
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

  addDriverModal() {
    const dialogRef = this.dialog.open(CreateDriverDialogComponent, {
      disableClose: false,
      autoFocus: true,
    });

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.driverService.saveDriver(this.carrierId, data)
            .subscribe(long => {
                console.log(long);
                this.loadDrivers();
              }, (err: any) => {
                this.error = err;
              }
            );
        }
      }
    );
  }
}
