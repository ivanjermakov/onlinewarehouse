import {Injectable} from '@angular/core';
import {API_BASE_URL} from "../../base-server-url";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../../auth/_services";
import saveAs from 'file-saver';
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  readonly companyId: number;
  readonly excelContentType: string = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient,
              private auth: AuthenticationService,
              private toast: RequestErrorToastHandlerService) {
    this.companyId = auth.getCompanyId();
  }

  getIncomeReport(): void {
    const path: string = `${this.baseApi}/${this.companyId}/reports/income-report`;
    this.toast.handleInfo('Please wait a moment. Download start just now.', 'Info');
    this.http.get(path).subscribe((byteArr: string) => {
      let blob = this.b64toBlob(byteArr, this.excelContentType);
      saveAs(blob, 'income report.xlsx');
    });
  }

  getPersonalLossReport(): void {
    const path: string = `${this.baseApi}/${this.companyId}/reports/personal-loss-report`;
    this.toast.handleInfo('Please wait a moment. Download start just now.', 'Info');
    this.http.get(path).subscribe((byteArr: string) => {
      let blob = this.b64toBlob(byteArr, this.excelContentType);
      saveAs(blob, 'personal loss report.xlsx');
    });
  }

  getProfitReport(): void {
    const path: string = `${this.baseApi}/${this.companyId}/reports/profit-report`;
    this.toast.handleInfo('Please wait a moment. Download start just now.', 'Info');
    this.http.get(path).subscribe((byteArr: string) => {
      let blob = this.b64toBlob(byteArr, this.excelContentType);
      saveAs(blob, 'profit report.xlsx');
    });
  }

  getWriteOffReport(): void {
    const path: string = `${this.baseApi}/${this.companyId}/reports/write-off-report`;
    this.toast.handleInfo('Please wait a moment. Download start just now.', 'Info');
    this.http.get(path).subscribe((byteArr: string) => {
      let blob = this.b64toBlob(byteArr, this.excelContentType);
      saveAs(blob, 'write-off report.xlsx');
    });
  }


  private b64toBlob(b64Data, contentType = '', sliceSize = 512) {
    const byteCharacters = atob(b64Data);
    const byteArrays = [];

    for (let offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      const slice = byteCharacters.slice(offset, offset + sliceSize);

      const byteNumbers = new Array(slice.length);
      for (let i = 0; i < slice.length; i++) {
        byteNumbers[i] = slice.charCodeAt(i);
      }

      const byteArray = new Uint8Array(byteNumbers);

      byteArrays.push(byteArray);
    }

    return new Blob(byteArrays, {type: contentType});
  }
}
