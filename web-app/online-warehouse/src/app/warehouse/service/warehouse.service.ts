import {Injectable} from '@angular/core';
import {API_BASE_URL} from "../../base-server-url";
import {HttpClient} from "@angular/common/http";
import {Pageable} from "../../shared/pagination/pageable";
import {Observable} from "rxjs";
import {Page} from "../../shared/pagination/page";
import HttpParamsBuilder from "../../shared/http/http-params-builder";
import {WarehouseDto} from "../dto/warehouse.dto";

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient) {
  }

  getWarehouses(pageable: Pageable, companyId: number): Observable<Page<WarehouseDto>> {
    const path: string = this.baseApi + '/' + companyId + '/warehouses';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    return this.http.get<Page<WarehouseDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  getWarehouse(companyId: number, warehouseId: number): Observable<WarehouseDto> {
    const path: string = this.baseApi + '/' + companyId + '/warehouses/' + warehouseId;
    return this.http.get<WarehouseDto>(path);
  }

  saveWarehouse(companyId: number, name: string): Observable<number> {
    const path: string = this.baseApi + '/' + companyId + '/warehouses';
    let body = `name: ${name}`;
    return this.http.post<number>(path, JSON.stringify(body));
  }
}
