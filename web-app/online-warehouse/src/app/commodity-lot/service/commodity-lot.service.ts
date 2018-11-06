import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {API_BASE_URL} from "../../base-server-url";
import {CommodityLotListDto} from "../dto/commodity-lot-list.dto";
import {CommodityLotFilter} from "../dto/commodity-lot.filter";
import {CreateCommodityLotDto} from "../dto/create-commodity-lot.dto";
import {CommodityLotDto} from "../dto/commodity-lot.dto";
import {Pageable} from "../../shared/pagination/pageable";
import {Page} from "../../shared/pagination/page";
import HttpParamsBuilder from "../../shared/http/http-params-builder";

@Injectable({
  providedIn: 'root'
})
export class CommodityLotService {

  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies/';

  getCommodityLots(filter: CommodityLotFilter, pageable: Pageable, companyId: number): Observable<Page<CommodityLotListDto>> {
    const path: string = this.baseApi + companyId + '/commodity-lots';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    return this.http.get<Page<CommodityLotListDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  saveCommodityLot(createCommodityLotDto: CreateCommodityLotDto, companyId: number): Observable<number> {
    const path: string = this.baseApi + companyId + '/commodity-lots';
    return this.http.post(path, createCommodityLotDto).pipe(
      map((data: number) => data)
    );
  }

  getCommodityLot(companyId: number, carrierId: number): Observable<CommodityLotDto> {
    const path: string = this.baseApi + companyId + '/commodity-lots/' + carrierId;
    return this.http.get<CommodityLotDto>(path);
  }
}
