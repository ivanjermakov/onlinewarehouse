import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../app.module";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies';

  getAllCompanies(): Observable<CompanyDto[]> {
    const path: string = this.baseApi;
    return this.http.get(path).pipe(
      map((data: any[]) => data.map(item => new CompanyDto(
        item.id,
        item.name,
        item.sizeType,
        item.change,
        item.actionType)))
    );
  }

  saveCompany(companyId: number, createCompanyDto: CreateCompanyDto):Observable<number> {
    const path: string = this.baseApi;
    return this.http.post(path, createCompanyDto).pipe(
      map((data: number) => data)
    );
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
