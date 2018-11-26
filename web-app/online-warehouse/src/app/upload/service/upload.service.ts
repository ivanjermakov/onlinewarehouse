import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_BASE_URL} from '../../base-server-url';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  constructor(private http: HttpClient) {
  }

  upload(file: File): Observable<any> {
    const fd = new FormData();
    fd.append('file', file);
    return this.http.post(API_BASE_URL + '/upload', fd);
  }

}
