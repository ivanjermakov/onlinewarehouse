import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Pageable} from "../shared/pagination/pageable";
import HttpParamsBuilder from "../shared/http/http-params-builder";
import {Page} from "../shared/pagination/page";
import {API_BASE_URL} from "../base-server-url";
import {PlacementDto} from "./dto/placement-dto";

@Injectable({
  providedIn: 'root'
})
export class PlacementService {

  constructor(private http: HttpClient) { }

  private baseApi: string = API_BASE_URL + '/companies';

  getPlacements(companyId: number, warehouseId: number, pageable: Pageable): Observable<Page<PlacementDto>> {
    const path: string = this.baseApi + '/' + companyId + '/warehouses/' + warehouseId + '/placements';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);

    return this.http.get<Page<PlacementDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  savePlacement(createPlacementDto: CreatePlacementDto, companyId: number, warehouseId: number): Observable<PlacementDto> {
    const path: string = this.baseApi + '/' + companyId + '/warehouses/' + warehouseId + '/placements';
    return this.http.post<PlacementDto>(path, createPlacementDto);
  }

  getPlacement(companyId: number, warehouseId: number, placementId: number): Observable<PlacementDto> {
    const path: string = this.baseApi + '/' + companyId + '/warehouses/' + warehouseId + '/placements' + placementId;
    return this.http.get<PlacementDto>(path);
  }

  editPlacement(createPlacementDto: CreatePlacementDto, companyId: number, warehouseId: number, placementId: number): Observable<PlacementDto> {
    const path: string = this.baseApi + '/' + companyId + '/warehouses/' + warehouseId + '/placements' + placementId;
    return this.http.put<PlacementDto>(path, createPlacementDto);
  }

  deletePlacement(companyId: number, warehouseId: number, placementId: number): void {
    const path: string = this.baseApi + '/' + companyId + '/warehouses/' + warehouseId + '/placements' + placementId;
    this.http.delete(path);
  }
}
