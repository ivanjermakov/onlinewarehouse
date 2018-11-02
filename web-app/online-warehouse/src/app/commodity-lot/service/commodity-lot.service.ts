import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {API_BASE_URL} from "../../base-server-url";

@Injectable({
  providedIn: 'root'
})
export class CommodityLotService {

  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies/';

  getCommodityLots(filter: CommodityLotFilter, companyId: number): Observable<CommodityLotListDto[]> {
    const path: string = this.baseApi + companyId + '/commodity-lots';
    let data = {};
    //Object.assign(data, filter);
    return this.http.get(path, {params: data}).pipe(
      map((data: any[]) => data.map(item => new CommodityLotListDto(
        item.id,
        item.counterpartyName,
        item.creation,
        item.commodityLotType)))
    );
  }

  saveCommodityLot(createCommodityLotDto: CreateCommodityLotDto, companyId: number): Observable<number> {
    const path: string = this.baseApi + companyId + '/commodity-lots';
    return this.http.post(path, createCommodityLotDto).pipe(
      map((data: number) => data)
    );
  }

  getCommodityLot(companyId: number, carrierId: number): Observable<CommodityLotDto> {
    const path: string = this.baseApi + companyId + '/commodity-lots/' + carrierId;
    return this.http.get(path).pipe(
      map((data: any) => data.map(item => new CommodityLotDto(
        item.id,
        item.counterpartyId,
        item.commodityLotType,
        item.creation,
        item.commodityLotGoodsDtoList)))
    );
  }
}
