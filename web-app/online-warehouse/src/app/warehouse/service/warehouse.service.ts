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
import {CreateConsignmentNoteDto} from "../../consignment-note/dto/create-consignment-note-dto";
import {CreateWriteOffActDto} from "../../write-off-act/dto/create-write-off-act.dto";
import {PlacementCreateWriteOffActDto} from "../../write-off-act/dto/placement-create-write-off-act.dto";
import {CommodityLotProfit} from "../distribute-goods-warehouse/distribute-goods-warehouse.component";

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {

  readonly companyId: number;
  private baseApi: string = API_BASE_URL + '/companies';
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

  updateWarehouse(warehouseDto: WarehouseDto, commodityLotProfitList: Array<CommodityLotProfit>): Observable<number> {
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses/' + warehouseDto.id;
    return this.http.put<number>(path, {value1: warehouseDto, value2: commodityLotProfitList});
  }

  getPlacement(warehouseId: number, placementId: number): Observable<PlacementDto> {
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses/'
      + warehouseId + '/placements/' + placementId;
    return this.http.get<PlacementDto>(path);
  }

  savePlacement(placementDto: PlacementDto) {
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses/' + placementDto.warehouseId + '/placements';
    return this.http.post<number>(path, placementDto);
  }

  updateWarehouseAndCreateConsignmentNote(warehouseDto: WarehouseDto, createConsignmentNoteDto: CreateConsignmentNoteDto): Observable<number> {
    createConsignmentNoteDto.creatorId = this.auth.getUserId();
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses/' + warehouseDto.id + '/create-consignment-note';
    return this.http.post<number>(path, {value1: warehouseDto, value2: createConsignmentNoteDto});
  }

  updateWarehouseAndCreateWriteOffAct(warehouseDto: WarehouseDto, createWriteOffActDto: CreateWriteOffActDto): Observable<number> {
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses/' + warehouseDto.id + '/create-write-off-act';
    return this.http.post<number>(path, {value1: warehouseDto, value2: createWriteOffActDto});
  }

  createPlacementWriteOffAct(warehouseId: number, placementId: number, placementCreateWriteOffActDto: PlacementCreateWriteOffActDto): Observable<number> {
    const path: string = this.baseApi + '/' + this.companyId + '/warehouses/'
      + warehouseId + '/placements/' + placementId + '/create-write-off-act';
    return this.http.post<number>(path, placementCreateWriteOffActDto)
  }
}
