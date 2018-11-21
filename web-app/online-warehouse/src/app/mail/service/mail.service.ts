import {Injectable} from '@angular/core';
import {API_BASE_URL} from '../../base-server-url';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BirthdayMailTemplateDto} from '../dto/BirthdayMailTemplateDto';

@Injectable({
  providedIn: 'root'
})
export class MailService {

  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient) {
  }

  getBirthdayMailTemplate(companyId: number): Observable<BirthdayMailTemplateDto> {
    const path: string = this.baseApi + '/' + companyId + '/mail-templates/birthday';
    return this.http.get<BirthdayMailTemplateDto>(path);
  }

}
