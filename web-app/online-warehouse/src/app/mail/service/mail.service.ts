import {Injectable} from '@angular/core';
import {API_BASE_URL} from '../../base-server-url';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {BirthdayMailTemplateDto} from '../dto/BirthdayMailTemplateDto';
import {UploadService} from '../../upload/service/upload.service';
import {AuthenticationService} from '../../auth/_services';

@Injectable({
  providedIn: 'root'
})
export class MailService {

  private baseApi: string = API_BASE_URL + '/companies';
  private readonly companyId: number;

  constructor(private http: HttpClient, private uploadService: UploadService,
              private auth: AuthenticationService) {
    this.companyId = auth.getCompanyId();
  }

  getBirthdayMailTemplate(): Observable<BirthdayMailTemplateDto> {
    const path: string = this.baseApi + '/' + this.companyId + '/mail-templates/birthday';
    return this.http.get<BirthdayMailTemplateDto>(path);
  }

  editBirthdayMailTemplate(birthdayMailTemplateDto: BirthdayMailTemplateDto, image: File,
                           response: (dto: BirthdayMailTemplateDto) => void) {
    this.uploadService.upload(image).subscribe((imagePath) => {
      birthdayMailTemplateDto.headerImagePath = imagePath;
      const path: string = this.baseApi + '/' + this.companyId + '/mail-templates/birthday';
      this.http.put<BirthdayMailTemplateDto>(path, birthdayMailTemplateDto).subscribe((p) => {
        response(p);
      });
    }, () => {
      birthdayMailTemplateDto.headerImagePath = null;
      const path: string = this.baseApi + '/' + this.companyId + '/mail-templates/birthday';
      this.http.put<BirthdayMailTemplateDto>(path, birthdayMailTemplateDto).subscribe((p) => {
        response(p);
      });
    });
  }

}
