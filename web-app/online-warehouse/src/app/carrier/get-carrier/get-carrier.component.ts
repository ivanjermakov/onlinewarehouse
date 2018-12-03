import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CarrierDto} from '../dto/carrier.dto';
import {CarrierService} from '../service/carrier.service';
import {BehaviorSubject} from 'rxjs';
import {finalize} from 'rxjs/operators';
import {CarrierTypeEnum} from "../dto/enum/carrier-type.enum";
import {MatDialog} from "@angular/material";
import {CreateDriverDialogComponent} from "../driver/create-driver-dialog/create-driver-dialog.component";
import {DriverService} from "../driver/driver.service";

@Component({
  selector: 'app-get-carrier',
  templateUrl: './get-carrier.component.html',
  styleUrls: ['./get-carrier.component.css']
})
export class GetCarrierComponent implements OnInit {

  carrierId: number;
  carrierDto: CarrierDto;
  private displayedColumns = ['id', 'name', 'address', 'taxNumber', 'carrierType', 'trusted'];
  private driverDisplayedColumns = ['seq', 'driverInfo'];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private error: any;

  private carrierTypeEnum = CarrierTypeEnum;

  constructor(
    private carrierService: CarrierService,
    private driverService: DriverService,
    private route: ActivatedRoute,
    private dialog: MatDialog) {
  }

  ngOnInit() {
    if (this.idExist()) {
      this.loadDriver();
    } else {
    }
  }

  idExist(): boolean {
    if (this.route.snapshot.paramMap.get('id') !== null) {
      this.carrierId = +this.route.snapshot.paramMap.get('id');
      return true;
    } else {
      return false;
    }
  }

  loadDriver() {
    this.loadingSubject.next(true);
    // TODO: don't forget to change to a variable
    this.carrierService.getCarrier(this.carrierId).pipe(
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(data => {
        this.carrierDto = data;
      }, (err: any) => {
        this.error = err;
      });
  }

  addDriverModal(): void {
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
                this.loadDriver();
              }, (err: any) => {
                this.error = err;
              }
            );
        }
      }
    );
  }

  changeTrustedValue() {
    this.carrierService.changeTrustedValue(this.carrierId)
      .subscribe(long => {
        console.log(long);
        this.loadDriver();
      }, (err: any) => {
        this.error = err;
      })
  }
}
