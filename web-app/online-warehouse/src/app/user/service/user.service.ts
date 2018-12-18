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
import {CreateUserDto} from "../dto/create-user.dto";
import {AuthorityDto} from "../dto/authority.dto";
import {User} from "../../auth/_models";
import {UserActivationDto} from "../dto/user-activation.dto";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  readonly companyId: number;
  baseApi = API_BASE_URL + '/companies/';

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
    this.companyId = this.auth.getCompanyId();
  }

  getAllUsers(userFilter: UserFilter, pageable: Pageable): Observable<Page<UserDto>> {
    const path: string = this.baseApi + this.companyId + '/users';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    userFilter.toUrlParameters(paramsBuilder);
    return this.http.get<Page<UserDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  getUserById(userId: number) {
    const path: string = this.baseApi + this.companyId + '/users/' + userId;
    return this.http.get(path);
  }

  validateUsername(username: string): Observable<boolean> {
    const path: string = this.baseApi + this.companyId + '/users/validate-username?username=' + username;
    return this.http.get<boolean>(path);
  }

  saveUser(createUserDto: CreateUserDto): Observable<number> {
    const path: string = this.baseApi + this.companyId + '/users';
    return this.http.post<number>(path, createUserDto);
  }

  changeAuthorities(userId: number, authorityDtoList: AuthorityDto[]): Observable<number> {
    const path: string = this.baseApi + this.companyId + '/users/' + userId + "/authorities";
    return this.http.put<number>(path, authorityDtoList);
  }

  changeEnableValue(userId: number): Observable<number> {
    const path: string = this.baseApi + this.companyId + '/users/' + userId + "/change-enabled";
    return this.http.post<number>(path, null);
  }

  resetPassword(userId: number): Observable<number> {
    const path: string = this.baseApi + this.companyId + '/users/' + userId + "/reset-password";
    return this.http.post<number>(path, null);
  }

  deleteUser(userId: number): Observable<number> {
    const path: string = this.baseApi + this.companyId + '/users/' + userId + "/set-deleted";
    return this.http.post<number>(path, null);
  }

  setPassword(user: UserActivationDto): Observable<boolean> {
    const path: string = API_BASE_URL + "/reset-password";
    return this.http.post<boolean>(path, user);
  }
}
