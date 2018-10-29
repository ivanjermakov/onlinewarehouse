import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../app.module";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies';

  getAllCompanies(): Observable<CompanyDto> {
    const path: string = this.baseApi;
    return this.http.get(path);
  }

  saveCompany(companyId: number, createCompanyDto: CreateCompanyDto) {
    const path: string = this.baseApi;
    return this.http.post(path, createCompanyDto);
  }

  setCompanyDisabled(companyId: number): void {
    const path: string = this.baseApi + companyId + '/disable';
    this.http.get(path);
  }

  setCompanyEnabled(companyId: number): void {
    const path: string = this.baseApi + companyId + '/enable';
    this.http.get(path);
  }

}
