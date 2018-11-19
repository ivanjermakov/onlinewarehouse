import {Injectable} from '@angular/core';
import {API_BASE_URL} from "../../base-server-url";
import {HttpClient} from "@angular/common/http";
import {Pageable} from "../../shared/pagination/pageable";
import {Observable} from "rxjs";
import {Page} from "../../shared/pagination/page";
import HttpParamsBuilder from "../../shared/http/http-params-builder";
import {WarehouseDto} from "../dto/warehouse.dto";
import {AuthenticationService} from "../../auth/_services";
import {CreateWarehouseDto} from "../dto/create-warehouse.dto";
import {PlacementDto} from "../dto/placement.dto";

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

  private baseApi: string = API_BASE_URL + '/companies';
  readonly companyId: number;
  private routedPlacement: PlacementDto;

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
    this.companyId = this.auth.getCompanyId();
  }

  getWarehouses(pageable: Pageable): Observable<Page<WarehouseDto>> {
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    return this.http.get<Page<WarehouseDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  getWarehouse(warehouseId: number): Observable<WarehouseDto> {
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses/' + warehouseId;
    return this.http.get<WarehouseDto>(path);
  }

  saveWarehouse(createWarehouseDto: CreateWarehouseDto): Observable<number> {
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses';
    return this.http.post<number>(path, createWarehouseDto);
  }

  updateWarehouse(warehouseDto: WarehouseDto): Observable<number> {
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses/' + warehouseDto.id;
    return this.http.put<number>(path, warehouseDto);
  }

  getPlacement(warehouseId: number, placementId: number): Observable<PlacementDto> {
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses/'
      + warehouseId + '/placements/' + placementId;
    return this.http.get<PlacementDto>(path);
  }
}
