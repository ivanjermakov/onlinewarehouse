import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Pageable} from "../shared/pagination/pageable";
import HttpParamsBuilder from "../shared/http/http-params-builder";
import {Page} from "../shared/pagination/page";
import {API_BASE_URL} from "../base-server-url";
import {ConsignmentNoteDto} from "./dto/consignment-note-dto";
import {CreateConsignmentNoteDto} from "./dto/create-consignment-note-dto";
import {ConsignmentNoteFilter} from "./dto/consignment-note-filter";
import {ConsignmentNoteListDto} from "./dto/consignment-note-list-dto";

@Injectable({
  providedIn: 'root'
})
export class ConsignmentNoteService {

  constructor(private http: HttpClient) {
  }

  private baseApi: string = API_BASE_URL + '/companies';

  getConsignmentNotes(companyId: number, consignmentNoteFilter: ConsignmentNoteFilter, pageable: Pageable): Observable<Page<ConsignmentNoteListDto>> {
    const path: string = this.baseApi + '/' + companyId + '/consignment-notes';
    let paramsBuilder = new HttpParamsBuilder();
    consignmentNoteFilter.toUrlParameters(paramsBuilder);
    pageable.toUrlParameters(paramsBuilder);

    return this.http.get<Page<ConsignmentNoteListDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  saveConsignmentNote(companyId: number, createConsignmentNoteDto: CreateConsignmentNoteDto): Observable<ConsignmentNoteDto> {
    const path: string = this.baseApi + '/' + companyId + '/consignment-notes';
    return this.http.post<ConsignmentNoteDto>(path, createConsignmentNoteDto);
  }

  getConsignmentNote(companyId: number, consignmentNoteId: number): Observable<ConsignmentNoteDto> {
    const path: string = this.baseApi + '/' + companyId + '/consignment-notes/' + consignmentNoteId;
    return this.http.get<ConsignmentNoteDto>(path);
  }
}
