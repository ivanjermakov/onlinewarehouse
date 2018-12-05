import {Component, OnInit} from '@angular/core';
import {API_BASE_URL} from "../base-server-url";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../auth/_services";
import {UserFilter} from "../user/dto/user-filter";
import {Pageable} from "../shared/pagination/pageable";
import {Observable} from "rxjs";
import {Page} from "../shared/pagination/page";
import {UserDto} from "../user/dto/user.dto";
import HttpParamsBuilder from "../shared/http/http-params-builder";
import {CreateUserDto} from "../user/dto/create-user.dto";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  readonly companyId: number;
  baseApi = API_BASE_URL + '/companies/';

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
    this.companyId = this.auth.getCompanyId();
  }

  ngOnInit() {
  }

  test() {
    console.log(this.auth.getCompanyId());
    const path: string = this.baseApi + 1 + '/users';
    let paramsBuilder = new HttpParamsBuilder();
    console.log(this.http.get<Page<UserDto>>(path).subscribe());
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
}
