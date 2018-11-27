import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CarrierDto} from '../dto/carrier.dto';
import {CarrierService} from '../service/carrier.service';
import {BehaviorSubject, of} from 'rxjs';
import {catchError, finalize} from 'rxjs/operators';

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
  private errors: any[];

  constructor(
    private carrierService: CarrierService,
    private route: ActivatedRoute) {
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
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(data => {
        if (data instanceof Array) {
          this.errors = data as any[];
        } else {
          this.carrierDto = data;
        }
      });
  }
}
