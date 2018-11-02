import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {CompanyDto} from "../dto/company.dto";
import {CreateCompanyDto} from "../dto/create-company.dto";
import {API_BASE_URL} from "../../base-server-url";

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies';

  getAllCompanies(): Observable<CompanyDto[]> {
    const path: string = this.baseApi;
    return this.http.get(path, {params: {page: '0', size: '20'}}).pipe(
      map((data: any[]) => data.map(item => new CompanyDto(
        item.id,
        item.name,
        item.sizeType,
        item.change,
        item.actionType)))
    );
  }

  saveCompany(createCompanyDto: CreateCompanyDto):Observable<number> {
    const path: string = this.baseApi;
    return this.http.post(path, createCompanyDto).pipe(
      map((data: number) => data)
    );
  }

  setCompanyDisabled(companyId: number): void {
    const path: string = this.baseApi + '/' + companyId + '/disable';
    this.http.patch(path, null).subscribe();
  }

  setCompanyEnabled(companyId: number): void {
    const path: string = this.baseApi + '/' + companyId + '/enable';
    this.http.patch(path, null).subscribe();
  }

}
