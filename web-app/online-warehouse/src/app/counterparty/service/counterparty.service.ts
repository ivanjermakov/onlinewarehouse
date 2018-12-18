import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../base-server-url";
import {Pageable} from "../../shared/pagination/pageable";
import {Observable} from "rxjs";
import {Page} from "../../shared/pagination/page";
import HttpParamsBuilder from "../../shared/http/http-params-builder";
import {CounterpartyFilter} from "../dto/counterparty.filter";
import {CounterpartyDto} from "../dto/counterparty.dto";
import {CreateCounterpartyDto} from "../dto/create-counterparty.dto";
import {AuthenticationService} from "../../auth/_services";

@Injectable({
  providedIn: 'root'
})
export class CounterpartyService {
  private baseApi: string = API_BASE_URL + '/companies/';
  private companyId: number;

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
    this.companyId = auth.getCompanyId();
  }

  getCounterparties(filter: CounterpartyFilter, pageable: Pageable): Observable<Page<CounterpartyDto>> {
    const path: string = this.baseApi + this.companyId + '/counterparties';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    return this.http.get<Page<CounterpartyDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  saveCounterparty(createCounterpartyDto: CreateCounterpartyDto): Observable<number> {
    const path: string = this.baseApi + this.companyId + '/counterparties';
    return this.http.post<number>(path, createCounterpartyDto);
  }


  getCounterparty(carrierId: number): Observable<CounterpartyDto> {
    const path: string = this.baseApi + this.companyId + '/counterparties/' + carrierId;
    return this.http.get<CounterpartyDto>(path);
  }
}
