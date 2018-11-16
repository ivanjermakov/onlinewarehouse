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
import {AuthenticationService} from "../auth/_services";

@Injectable({
  providedIn: 'root'
})
export class ConsignmentNoteService {

  constructor(private http: HttpClient,
              private auth: AuthenticationService) {
  }

  private baseApi: string = API_BASE_URL + '/companies';

  getConsignmentNotes(consignmentNoteFilter: ConsignmentNoteFilter, pageable: Pageable): Observable<Page<ConsignmentNoteListDto>> {
    var companyId = this.auth.getCompanyId();
    const path: string = this.baseApi + '/' + companyId + '/consignment-notes';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    if (consignmentNoteFilter) {
      paramsBuilder.addObject(consignmentNoteFilter);
    }

    return this.http.get<Page<ConsignmentNoteListDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  getConsignmentNote(consignmentNoteId: number): Observable<ConsignmentNoteDto> {
    var companyId = this.auth.getCompanyId();
    const path: string = this.baseApi + '/' + companyId + '/consignment-notes/' + consignmentNoteId;
    return this.http.get<ConsignmentNoteDto>(path);
  }

  saveConsignmentNote(createConsignmentNoteDto: CreateConsignmentNoteDto): Observable<ConsignmentNoteDto> {
    var companyId = this.auth.getCompanyId();
    createConsignmentNoteDto.creatorId = this.auth.getUserId();
    const path: string = this.baseApi + '/' + companyId + '/consignment-notes';
    return this.http.post<ConsignmentNoteDto>(path, createConsignmentNoteDto);
  }
}
