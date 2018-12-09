import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {API_BASE_URL} from '../../base-server-url';
import {AuthenticationService} from '../../auth/_services';

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  private readonly companyId: number;

  constructor(private http: HttpClient, private auth: AuthenticationService) {
    this.companyId = auth.getCompanyId();
  }

  upload(file: File): Observable<any> {
    const fd = new FormData();
    fd.append('file', file);
    return this.http.post(API_BASE_URL + '/companies/' + this.companyId + '/upload', fd);
  }

}
