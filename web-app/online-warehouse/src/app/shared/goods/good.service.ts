import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../base-server-url";
import {Observable} from "rxjs";
import {GoodsDto} from "./goods.dto";
import {Pageable} from "../pagination/pageable";
import {Page} from "../pagination/page";
import HttpParamsBuilder from "../http/http-params-builder";

@Injectable({
  providedIn: 'root'
})

export class GoodService {

  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient) {
  }

  getAllGoods(companyId: number, pageable: Pageable): Observable<Page<GoodsDto>> {
    const path: string = this.baseApi + '/' + companyId + '/goods';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    return this.http.get<Page<GoodsDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

}
