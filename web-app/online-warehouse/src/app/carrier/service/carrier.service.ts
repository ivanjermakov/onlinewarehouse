import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {API_BASE_URL} from "../../base-server-url";

@Injectable({
  providedIn: 'root'
})
export class CarrierService {

  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies';

  getCarriers(filter: CarrierFilter, companyId: number): Observable<CarrierListDto[]> {
    const path: string = this.baseApi + '/' + companyId + '/carriers';
    let data = {};
    //Object.assign(data, filter);
    return this.http.get(path, {params: data}).pipe(
      map((data: any[]) => data.map(item => new CarrierListDto(
        item.id,
        item.name,
        item.taxNumber,
        item.carrierType,
        item.trusted)))
    );
  }

  saveCarrier(createCarrierDto: CreateCarrierDto, companyId: number): Observable<number> {
    const path: string = this.baseApi + '/' + companyId + '/carriers';
    return this.http.post(path, createCarrierDto).pipe(
      map((data: number) => data)
    );
  }

  getCarrier(companyId: number, carrierId: number): Observable<CarrierDto> {
    const path: string = this.baseApi + '/' + companyId + '/carriers/' + carrierId;
    return this.http.get(path).pipe(
      map((data: any) => data.map(item => new CarrierDto(
        item.id,
        item.addressCountry,
        item.addressRegion,
        item.addressLocality,
        item.name,
        item.taxNumber,
        item.carrierType,
        item.trusted,
        item.drivers)))
    );
  }

  editCarrier(createCarrierDto: CreateCarrierDto, companyId: number, carrierId: number): Observable<number> {
    const path: string = this.baseApi + '/' + companyId + '/carriers/' + carrierId;
    return this.http.put(path, createCarrierDto).pipe(
      map((data: number) => data)
    );
  }

  deleteCarrier(companyId: number, carrierId: number): void {
    const path: string = this.baseApi + '/' + companyId + '/carriers/' + carrierId;
    this.http.delete(path);
  }
}
