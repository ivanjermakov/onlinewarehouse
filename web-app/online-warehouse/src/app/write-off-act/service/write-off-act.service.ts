import {Injectable} from '@angular/core';
import {API_BASE_URL} from "../../base-server-url";
import {HttpClient} from "@angular/common/http";
import {WriteOffActFilter} from "../dto/write-off-act.filter";
import {Observable} from "rxjs";
import {WriteOffActListDto} from "../dto/write-off-act-list.dto";
import {map} from "rxjs/operators";
import {CreateWriteOffActDto} from "../dto/create-write-off-act.dto";
import {WriteOffActDto} from "../dto/write-off-act.dto";

@Injectable({
  providedIn: 'root'
})


export class WriteOffActService {

  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient) {
  }

  getWriteOffActs(companyId: number, filter: WriteOffActFilter): Observable<WriteOffActListDto[]> {
    const path: string = this.baseApi + '/' + companyId + '/write-off-acts';
    let data = {};
    let to = filter.to ? filter.to.toISOString().split('T')[0] : '';
    let from = filter.from ? filter.from.toISOString().split('T')[0] : '';
    Object.assign(data, {page: '0', size: '20', to: to, from: from});
    return this.http.get(path, {params: data}).pipe(
      map((data: any[]) => data.map(item => new WriteOffActListDto(
        item.id,
        item.creation,
        item.creatorId,
        item.responsiblePerson,
        item.totalAmount,
        item.writeOffActType)))
    );
  }

  saveWriteOffAct(companyId: number, createWriteOffActDto: CreateWriteOffActDto): Observable<number> {
    const path: string = this.baseApi + '/' + companyId + '/write-off-acts';
    return this.http.post(path, createWriteOffActDto).pipe(
      map((data: number) => data)
    );
  }

  getWriteOffAct(companyId: number, writeOffActId: number): Observable<WriteOffActDto> {
    const path: string = this.baseApi + '/' + companyId + '/write-off-acts/' + writeOffActId;
    return this.http.get<WriteOffActDto>(path);
  }
}
