import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "./base-server-url";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private _http: HttpClient) {
  }

  baseApi = API_BASE_URL + '/companies/';

  getAllUsers(companyId) {
    // let path: string;
    const path: string = this.baseApi + companyId + '/users';
    // path = this._baseApi + '/companies/' + companyId + '/users';
    return this._http.get(path);
    // return this._http.get('https://jsonplaceholder.typicode.com/users');
  }

  getUserById(companyId, userId) {
    const path: string = this.baseApi + companyId + '/users/' + userId;
    return this._http.get(path);
  }
}
