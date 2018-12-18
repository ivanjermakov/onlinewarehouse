import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

import {User} from '../_models';
import {Observable} from "rxjs";
import {ConsignmentNoteDto} from "../../consignment-note/dto/consignment-note-dto";
import {API_BASE_URL} from "../../base-server-url";

@Injectable()
export class UserService {
  private baseApi: string = API_BASE_URL;

  constructor(private http: HttpClient) {
  }

  getAll() {
    return this.http.get<User[]>('/api/users');
  }
}
