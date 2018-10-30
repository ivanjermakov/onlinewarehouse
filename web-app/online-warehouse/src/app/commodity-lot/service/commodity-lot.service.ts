import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../app.module";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CommodityLotService {

  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies/';

  getCommodityLots(filter: CommodityLotFilter, companyId: number): Observable<CommodityLotListDto> {
    const path: string = this.baseApi + companyId + '/commodity-lots';
    let data = {};
    //Object.assign(data, filter);
    return this.http.get(path, {params: data});
  }

  saveCommodityLot(createCommodityLotDto: CreateCommodityLotDto, companyId: number): Observable<number> {
    const path: string = this.baseApi + companyId + '/commodity-lots';
    return this.http.post(path, createCommodityLotDto);
  }

  getCommodityLot(companyId: number, carrierId: number): Observable<CommodityLotDto> {
    const path: string = this.baseApi + companyId + '/commodity-lots/' + carrierId;
    return this.http.get(path);
  }
}
