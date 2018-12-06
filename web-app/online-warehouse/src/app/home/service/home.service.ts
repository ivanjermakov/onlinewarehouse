import {Injectable} from '@angular/core';
import {API_BASE_URL} from "../../base-server-url";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../../auth/_services";
import {HomeCard} from "../home-card";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  readonly companyId: number;
  baseApi = API_BASE_URL + '/companies/';

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
    this.companyId = this.auth.getCompanyId();
  }

  getHomeCards(): Observable<HomeCard[]> {
    const path: string = this.baseApi + this.companyId + '/home-cards';
    return this.http.get<HomeCard[]>(path);
  }

  saveCard(homeCard: HomeCard): Observable<number> {
    const path: string = this.baseApi + this.companyId + '/home-cards';
    return this.http.post<number>(path, homeCard);
  }
}
