import {Injectable} from '@angular/core';
import {API_BASE_URL} from "../../../base-server-url";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../../../auth/_services";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  readonly companyId: number;
  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
    this.companyId = auth.getCompanyId();
  }

  getCountries(): Observable<any[]> {
    const path: string = this.baseApi + '/' + this.companyId + '/address/countries';
    return this.http.get<any[]>(path);
  }

  getRegions(countryId: number): Observable<any[]> {
    const path: string = this.baseApi + '/' + this.companyId + '/address/countries/' + countryId + '/regions';
    return this.http.get<any[]>(path);
  }
}
