import {Injectable} from '@angular/core';
import {API_BASE_URL} from "../../base-server-url";
import {HttpClient} from "@angular/common/http";
import {AuthenticationService} from "../../auth/_services";
import saveAs from 'file-saver';
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {ReportDateFilter} from "../dto/report-date.filter";
import HttpParamsBuilder from "../../shared/http/http-params-builder";
import {PieChartDto} from "../dto/pie-chart.dto";
import {Observable} from "rxjs";

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


  getIncomeReport(filter: ReportDateFilter): void {
    const path: string = `${this.baseApi}/${this.companyId}/reports/income-report`;
    this.toast.handleInfo('Please wait a moment. Download start just now.', 'Info');
    let paramsBuilder = new HttpParamsBuilder();
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    this.http.get(path, {params: paramsBuilder.getHttpParams()}).subscribe((byteArr: string) => {
      let blob = this.b64toBlob(byteArr, this.excelContentType);
      saveAs(blob, 'income report.xlsx');
    });
  }

  getPersonalLossReport(filter: ReportDateFilter): void {
    const path: string = `${this.baseApi}/${this.companyId}/reports/personal-loss-report`;
    this.toast.handleInfo('Please wait a moment. Download start just now.', 'Info');
    let paramsBuilder = new HttpParamsBuilder();
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    this.http.get(path, {params: paramsBuilder.getHttpParams()}).subscribe((byteArr: string) => {
      let blob = this.b64toBlob(byteArr, this.excelContentType);
      saveAs(blob, 'personal loss report.xlsx');
    });
  }

  getProfitReport(filter: ReportDateFilter): void {
    const path: string = `${this.baseApi}/${this.companyId}/reports/profit-report`;
    this.toast.handleInfo('Please wait a moment. Download start just now.', 'Info');
    let paramsBuilder = new HttpParamsBuilder();
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    this.http.get(path, {params: paramsBuilder.getHttpParams()}).subscribe((byteArr: string) => {
      let blob = this.b64toBlob(byteArr, this.excelContentType);
      saveAs(blob, 'profit report.xlsx');
    });
  }

  getWriteOffReport(filter: ReportDateFilter): void {
    const path: string = `${this.baseApi}/${this.companyId}/reports/write-off-report`;
    this.toast.handleInfo('Please wait a moment. Download start just now.', 'Info');
    let paramsBuilder = new HttpParamsBuilder();
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    this.http.get(path, {params: paramsBuilder.getHttpParams()}).subscribe((byteArr: string) => {
      let blob = this.b64toBlob(byteArr, this.excelContentType);
      saveAs(blob, 'write-off report.xlsx');
    });
  }

  getClientReport(filter: ReportDateFilter): void {
    const path: string = `${this.baseApi}/report`;
    this.toast.handleInfo('Please wait a moment. Download start just now.', 'Info');
    let paramsBuilder = new HttpParamsBuilder();
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    this.http.get(path, {params: paramsBuilder.getHttpParams()}).subscribe((byteArr: string) => {
      let blob = this.b64toBlob(byteArr, this.excelContentType);
      saveAs(blob, 'client report.xlsx');
    });
  }

  getUserRolesStatistics(): Observable<PieChartDto[]> {
    const path: string = `${API_BASE_URL}/companies/${this.companyId}/users/role-statistics`;
    return this.http.get<PieChartDto[]>(path);
  }

  getInCounterpartiesGoodsCostStatistics(): Observable<PieChartDto[]> {
    const path: string = `${API_BASE_URL}/companies/${this.companyId}/counterparties/goods-in-statistics`;
    return this.http.get<PieChartDto[]>(path);
  }

  getOutCounterpartiesGoodsCostStatistics(): Observable<PieChartDto[]> {
    const path: string = `${API_BASE_URL}/companies/${this.companyId}/counterparties/goods-out-statistics`;
    return this.http.get<PieChartDto[]>(path);
  }

  getActCreatorsStatistics(): Observable<PieChartDto[]> {
    const path: string = `${API_BASE_URL}/companies/${this.companyId}/write-off-acts/write-off-statistics`;
    return this.http.get<PieChartDto[]>(path);
  }

  // getPaymentStatistics(): Observable<any[]> {
  //     const path: string = `${API_BASE_URL}/companies/${this.companyId}/reports/payment-statistics`;
  //   return this.http.get(path);
  // }


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
