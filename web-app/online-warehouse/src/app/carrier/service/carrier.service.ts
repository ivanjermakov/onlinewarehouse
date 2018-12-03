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

@Injectable({
  providedIn: 'root'
})
export class CarrierService {

  readonly companyId: number;
  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
    this.companyId = auth.getCompanyId();
  }

  getCarriers(filter: CarrierFilter, pageable: Pageable): Observable<Page<CarrierListDto>> {
    const path: string = this.baseApi + '/' + this.companyId + '/carriers';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    return this.http.get<Page<CarrierListDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  saveCarrier(createCarrierDto: CreateCarrierDto): Observable<number> {
    const path: string = this.baseApi + '/' + this.companyId + '/carriers';
    return this.http.post(path, createCarrierDto).pipe(
      map((data: number) => data)
    );
  }

  getCarrier(carrierId: number): Observable<CarrierDto> {
    const path: string = this.baseApi + '/' + this.companyId + '/carriers/' + carrierId;
    return this.http.get<CarrierDto>(path);
  }

  editCarrier(createCarrierDto: CreateCarrierDto, carrierId: number): Observable<number> {
    const path: string = this.baseApi + '/' + this.companyId + '/carriers/' + carrierId;
    return this.http.put(path, createCarrierDto).pipe(
      map((data: number) => data)
    );
  }

  changeTrustedValue(carrierId: number): Observable<number> {
    const path: string = this.baseApi + '/' + this.companyId + '/carriers/' + carrierId + '/change-trusted';
    return this.http.post<number>(path, null);
  }

  deleteCarrier(carrierId: number): void {
    const path: string = this.baseApi + '/' + this.companyId + '/carriers/' + carrierId;
    this.http.delete(path);
  }
}
