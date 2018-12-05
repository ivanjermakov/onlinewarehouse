import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../base-server-url";
import {Observable} from "rxjs";
import {GoodsDto} from "./dto/goods.dto";
import {Pageable} from "../pagination/pageable";
import {Page} from "../pagination/page";
import HttpParamsBuilder from "../http/http-params-builder";
import {AuthenticationService} from "../../auth/_services";
import {GoodFilter} from "./dto/good-filter";

@Injectable({
  providedIn: 'root'
})

export class GoodService {

  readonly companyId: number;
  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
    this.companyId = this.auth.getCompanyId();
  }

  getAllGoods(goodFilter: GoodFilter, pageable: Pageable): Observable<Page<GoodsDto>> {
    const path: string = this.baseApi + '/' + this.companyId + '/goods';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    goodFilter.toUrlParameters(paramsBuilder);
    return this.http.get<Page<GoodsDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  saveGoods(goodsDto: GoodsDto): Observable<number> {
    const path: string = this.baseApi + '/' + this.companyId + '/goods';
    return this.http.post<number>(path, goodsDto);
  }
}
