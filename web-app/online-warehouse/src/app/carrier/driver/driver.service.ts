import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {API_BASE_URL} from "../../base-server-url";
import {CarrierFilter} from "../dto/carrier.filter";
import {CarrierListDto} from "../dto/carrier-list.dto";
import {CreateCarrierDto} from "../dto/create-carrier.dto";
import {CarrierDto} from "../dto/carrier.dto";
import {Page} from "../../shared/pagination/page";
import HttpParamsBuilder from "../../shared/http/http-params-builder";
import {Pageable} from "../../shared/pagination/pageable";
import {AuthenticationService} from "../../auth/_services";
import {DriverDto} from "./driver.dto";

@Injectable({
  providedIn: 'root'
})
export class DriverService {

  readonly companyId: number;
  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
    this.companyId = auth.getCompanyId();
  }

  getDrivers(carrierId: number, pageable: Pageable): Observable<Page<DriverDto>> {
    const path: string = this.baseApi + '/' + this.companyId + '/carriers/' + carrierId + "/drivers";
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);

    return this.http.get<Page<DriverDto>>(path, {params: paramsBuilder.getHttpParams()});
  }
}
