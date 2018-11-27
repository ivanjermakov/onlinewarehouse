import {Injectable} from '@angular/core';
import {API_BASE_URL} from "../../base-server-url";
import {HttpClient} from "@angular/common/http";
import {WriteOffActFilter} from "../dto/write-off-act.filter";
import {Observable} from "rxjs";
import {WriteOffActListDto} from "../dto/write-off-act-list.dto";
import {map} from "rxjs/operators";
import {CreateWriteOffActDto} from "../dto/create-write-off-act.dto";
import {WriteOffActDto} from "../dto/write-off-act.dto";
import HttpParamsBuilder from "../../shared/http/http-params-builder";
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {AuthenticationService} from "../../auth/_services";
import {CreateCommodityLotDto} from "../../commodity-lot/dto/create-commodity-lot.dto";

@Injectable({
  providedIn: 'root'
})


export class WriteOffActService {

  readonly companyId: number;
  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
    this.companyId = auth.getCompanyId();
  }

  getWriteOffActs(filter: WriteOffActFilter, pageable: Pageable): Observable<Page<WriteOffActListDto>> {
    const path: string = this.baseApi + '/' + this.companyId + '/write-off-acts';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    return this.http.get<Page<WriteOffActListDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  saveWriteOffAct(createWriteOffActDto: CreateWriteOffActDto): Observable<number> {
    const path: string = this.baseApi + '/' + this.companyId + '/write-off-acts';
    return this.http.post(path, createWriteOffActDto).pipe(
      map((data: number) => data)
    );
  }

  saveWriteOffActAndCommodityLot(createWriteOffActDto: CreateWriteOffActDto, createCommodityLotDto: CreateCommodityLotDto): Observable<any> {
    const path: string = this.baseApi + '/' + this.companyId + '/write-off-acts/create-commodity-lot';
    let pair = {value1: createWriteOffActDto, value2: createCommodityLotDto};
    return this.http.put<any>(path, pair);
  }

  getWriteOffAct(writeOffActId: number): Observable<WriteOffActDto> {
    const path: string = this.baseApi + '/' + this.companyId + '/write-off-acts/' + writeOffActId;
    return this.http.get<WriteOffActDto>(path);
  }
}
