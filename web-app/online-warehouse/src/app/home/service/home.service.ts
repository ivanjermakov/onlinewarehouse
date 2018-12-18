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

  baseApi = API_BASE_URL + '/companies/';

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
  }

  getHomeCards(): Observable<HomeCard[]> {
    var companyId = this.auth.getCompanyId();
    const path: string = this.baseApi + companyId + '/home-cards';
    return this.http.get<HomeCard[]>(path);
  }

  saveCard(homeCard: HomeCard): Observable<number> {
    var companyId = this.auth.getCompanyId();
    const path: string = this.baseApi + companyId + '/home-cards';
    return this.http.post<number>(path, homeCard);
  }
}
