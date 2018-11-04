import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {API_BASE_URL} from "../../base-server-url";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {GoodsDto} from "./goods.dto";

@Injectable({
  providedIn: 'root'
})

export class GoodService {

  private baseApi: string = API_BASE_URL + '/companies';

  constructor(private http: HttpClient) {
  }

  getAllGoods(companyId: number): Observable<GoodsDto[]> {
    const path: string = this.baseApi + '/' + companyId + '/goods';
    return this.http.get(path, {params: {page: '0', size: '20'}}).pipe(
      map((data: any[]) => data.map(item => new GoodsDto(
        item.id,
        item.name,
        item.placementType,
        item.measurementUnit,
        item.cost,
        item.weight,
        item.labelling,
        item.description)))
    );
  }
}
