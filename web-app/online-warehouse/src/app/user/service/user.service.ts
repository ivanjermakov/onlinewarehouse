import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../base-server-url";
import {Pageable} from "../../shared/pagination/pageable";
import {Observable} from "rxjs";
import {Page} from "../../shared/pagination/page";
import HttpParamsBuilder from "../../shared/http/http-params-builder";
import {UserDto} from "../dto/user.dto";
import {UserFilter} from "../dto/user-filter";
import {AuthenticationService} from "../../auth/_services";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseApi = API_BASE_URL + '/companies/';

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
  }

  getAllUsers(userFilter: UserFilter, pageable: Pageable): Observable<Page<UserDto>> {
    var companyId = this.auth.getCompanyId();
    const path: string = this.baseApi + companyId + '/users';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    userFilter.toUrlParameters(paramsBuilder);
    return this.http.get<Page<UserDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  getUserById(companyId, userId) {
    const path: string = this.baseApi + companyId + '/users/' + userId;
    return this.http.get(path);
  }
}
