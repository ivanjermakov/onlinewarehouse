import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../base-server-url";
import {Pageable} from "../../shared/pagination/pageable";
import {Observable} from "rxjs";
import {Page} from "../../shared/pagination/page";
import HttpParamsBuilder from "../../shared/http/http-params-builder";
import {CounterpartyFilter} from "../dto/counterparty.filter";
import {CounterpartyDto} from "../dto/counterparty.dto";

@Injectable({
  providedIn: 'root'
})
export class CounterpartyService {
  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies/';

  getCounterparties(filter: CounterpartyFilter, pageable: Pageable, companyId: number): Observable<Page<CounterpartyDto>> {
    const path: string = this.baseApi + companyId + '/counterparties';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    return this.http.get<Page<CounterpartyDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  // saveCommodityLot(createCommodityLotDto: CreateCommodityLotDto, companyId: number): Observable<number> {
  //   const path: string = this.baseApi + companyId + '/counterparties';
  //   return this.http.post(path, createCommodityLotDto).pipe(
  //     map((data: number) => data)
  //   );
  // }
  //
  // getCommodityLot(companyId: number, carrierId: number): Observable<CommodityLotDto> {
  //   const path: string = this.baseApi + companyId + '/counterparties/' + carrierId;
  //   return this.http.get<CommodityLotDto>(path);
  // }
}
