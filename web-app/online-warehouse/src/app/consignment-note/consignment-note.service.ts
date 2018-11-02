import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Pageable} from "../shared/pagination/pageable";
import HttpParamsBuilder from "../shared/http/http-params-builder";
import {Page} from "../shared/pagination/page";
import {API_BASE_URL} from "../base-server-url";

@Injectable({
  providedIn: 'root'
})
export class PlacementService {

  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies';

  getPlacements(companyId: number, consignmentNoteType: string, from: string, to: string, pageable: Pageable): Observable<Page<ConsignmentNoteDto>> {
    const path: string = this.baseApi + '/' + companyId + '/consignment-notes';
    let paramsBuilder = new HttpParamsBuilder();
    paramsBuilder.addIfNotEmpty('consignmentNoteType', consignmentNoteType);
    paramsBuilder.addIfNotEmpty('from', from);
    paramsBuilder.addIfNotEmpty('to', to);
    pageable.toUrlParameters(paramsBuilder);

    return this.http.get<Page<ConsignmentNoteDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  savePlacement(createConsignmentNoteDto: CreateConsignmentNoteDto, companyId: number): Observable<ConsignmentNoteDto> {
    const path: string = this.baseApi + '/' + companyId + '/consignment-notes';
    return this.http.post<ConsignmentNoteDto>(path, createConsignmentNoteDto);
  }

  getPlacement(companyId: number, consignmentNoteId: number): Observable<ConsignmentNoteDto> {
    const path: string = this.baseApi + '/' + companyId + '/consignment-notes/' + consignmentNoteId;
    return this.http.get<ConsignmentNoteDto>(path);
  }
}
