import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from "rxjs/operators";
import {API_BASE_URL} from "../../base-server-url";
import {JwtHelperService} from "@auth0/angular-jwt";
import {User} from "../_models";

@Injectable()
export class AuthenticationService {
  private baseApi: string = API_BASE_URL + '/api/authenticate';
  private jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    console.log('test');
    return this.http.post<any>(this.baseApi, {username: username, password: password})
      .pipe(map(user => {
        // login successful if there's a jwt token in the response
        if (user && user.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(user));
        }

        return user;
      }));
  }

  register(user: User) {
    return this.http.post(`${API_BASE_URL}/api/register`, user);
  }


  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
  }

  getAuthorities(): Array<any> {
    if (localStorage.getItem('currentUser')) {
      let decodeToken = this.jwtHelper.decodeToken(localStorage.getItem('currentUser'));
      return decodeToken.authorities;
    }
    return null;
  }

  getCompanyId(): number {
    if (localStorage.getItem('currentUser')) {
      let decodeToken = this.jwtHelper.decodeToken(localStorage.getItem('currentUser'));
      return decodeToken.companyId;
    }
    return null;
  }

  getUserId(): number {
    if (localStorage.getItem('currentUser')) {
      let decodeToken = this.jwtHelper.decodeToken(localStorage.getItem('currentUser'));
      return decodeToken.userId;
    }
    return null;
  }

  getCompanyName(): string {
    if (localStorage.getItem('currentUser')) {
      let decodeToken = this.jwtHelper.decodeToken(localStorage.getItem('currentUser'));
      return decodeToken.companyName;
    }
    return null;
  }
}
