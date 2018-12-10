import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {CompanyDto} from "../dto/company.dto";
import {CreateCompanyDto} from "../dto/create-company.dto";
import {API_BASE_URL} from "../../base-server-url";
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import HttpParamsBuilder from "../../shared/http/http-params-builder";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient) {
  }

  getAllCompanies(pageable: Pageable): Observable<Page<CompanyDto>> {
    const path: string = this.baseApi;
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    return this.http.get<Page<CompanyDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  saveCompany(createCompanyDto: CreateCompanyDto): Observable<number> {
    console.log(createCompanyDto);
    const path: string = this.baseApi;
    return this.http.post(path, createCompanyDto).pipe(
      map((data: number) => data)
    );
  }

  setCompanyDisabled(companyId: number): Observable<any> {
    const path: string = this.baseApi + '/' + companyId + '/disable';
    return this.http.patch(path, null);
  }

  setCompanyEnabled(companyId: number): Observable<any> {
    const path: string = this.baseApi + '/' + companyId + '/enable';
    return this.http.patch(path, null);
  }

}
