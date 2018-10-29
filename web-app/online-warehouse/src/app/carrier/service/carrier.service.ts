import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../app.module";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CarrierService {

  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies';

  getCarriers(filter: CarrierFilter, companyId: number): Observable<CarrierListDto> {
    const path: string = this.baseApi + '/' + companyId + '/carriers';
    let data = {};
    Object.assign(data, filter);
    return this.http.get(path, {params: data});
  }

  saveCarrier(createCarrierDto: CreateCarrierDto, companyId: number): Observable<number> {
    const path: string = this.baseApi + '/' + companyId + '/carriers';
    return this.http.post(path, createCarrierDto);
  }

  getCarrier(companyId: number, carrierId: number): Observable<CarrierDto> {
    const path: string = this.baseApi + '/' + companyId + '/carriers/' + carrierId;
    return this.http.get(path);
  }

  editCarrier(createCarrierDto: CreateCarrierDto, companyId: number, carrierId: number): Observable<number> {
    const path: string = this.baseApi + '/' + companyId + '/carriers/' + carrierId;
    return this.http.put(path, createCarrierDto);
  }

  deleteCarrier(companyId: number, carrierId: number): void {
    const path: string = this.baseApi + '/' + companyId + '/carriers/' + carrierId;
    this.http.delete(path);
  }
}
