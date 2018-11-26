import {Injectable} from '@angular/core';
import {API_BASE_URL} from '../../base-server-url';
import {HttpClient} from '@angular/common/http';
import {Observable, Subscription} from 'rxjs';
import {BirthdayMailTemplateDto} from '../dto/BirthdayMailTemplateDto';
import {UploadService} from '../../upload/service/upload.service';

@Injectable({
  providedIn: 'root'
})
export class MailService {

  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient, private uploadService: UploadService) {
  }

  getBirthdayMailTemplate(companyId: number): Observable<BirthdayMailTemplateDto> {
    const path: string = this.baseApi + '/' + companyId + '/mail-templates/birthday';
    return this.http.get<BirthdayMailTemplateDto>(path);
  }

  editBirthdayMailTemplate(birthdayMailTemplateDto: BirthdayMailTemplateDto, image: File, companyId: number,
                           response: (dto: BirthdayMailTemplateDto) => void) {
    this.uploadService.upload(image).subscribe((imagePath) => {
      birthdayMailTemplateDto.headerImagePath = imagePath;
      const path: string = this.baseApi + '/' + companyId + '/mail-templates/birthday';
      this.http.put<BirthdayMailTemplateDto>(path, birthdayMailTemplateDto).subscribe((p) => {
        response(p);
      });
    });
  }

}
