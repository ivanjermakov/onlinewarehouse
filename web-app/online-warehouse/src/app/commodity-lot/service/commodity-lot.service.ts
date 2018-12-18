import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {API_BASE_URL} from "../../base-server-url";
import {CommodityLotListDto} from "../dto/commodity-lot-list.dto";
import {CommodityLotFilter} from "../dto/commodity-lot.filter";
import {CreateCommodityLotDto} from "../dto/create-commodity-lot.dto";
import {CommodityLotDto} from "../dto/commodity-lot.dto";
import {Pageable} from "../../shared/pagination/pageable";
import {Page} from "../../shared/pagination/page";
import HttpParamsBuilder from "../../shared/http/http-params-builder";
import {AuthenticationService} from "../../auth/_services";
import {ConsignmentNoteDto} from "../../consignment-note/dto/consignment-note-dto";
import {CreateCommodityLotGoodsDto} from "../dto/create-commodity-lot-goods.dto";
import {CommodityLotTypeEnum} from "../dto/enum/commodity-lot-type.enum";
import {CreateWriteOffActDto} from "../../write-off-act/dto/create-write-off-act.dto";
import {WriteOffActService} from "../../write-off-act/service/write-off-act.service";

@Injectable({
  providedIn: 'root'
})
export class CommodityLotService {

  private companyId: number;
  private baseApi: string = API_BASE_URL + '/companies/';

  constructor(private http: HttpClient,
              private auth: AuthenticationService,
              private writeOffActService: WriteOffActService) {
    this.companyId = auth.getCompanyId();
  }

  getCommodityLots(filter: CommodityLotFilter, pageable: Pageable): Observable<Page<CommodityLotListDto>> {
    const path: string = this.baseApi + this.companyId + '/commodity-lots';
    let paramsBuilder = new HttpParamsBuilder();
    pageable.toUrlParameters(paramsBuilder);
    if (filter) {
      paramsBuilder.addObject(filter);
    }
    return this.http.get<Page<CommodityLotListDto>>(path, {params: paramsBuilder.getHttpParams()});
  }

  saveCommodityLot(createCommodityLotDto: CreateCommodityLotDto): Observable<number> {
    const path: string = this.baseApi + this.companyId + '/commodity-lots';
    return this.http.post(path, createCommodityLotDto).pipe(
      map((data: number) => data)
    );
  }

  setCommodityLotProcessed(commodityLotId: number): Observable<number> {
    const path: string = this.baseApi + this.companyId + '/commodity-lots/' + commodityLotId + '?status=PROCESSED';
    return this.http.put<number>(path, null);
  }

  getCommodityLot(commodityLotId: number): Observable<CommodityLotDto> {
    const path: string = this.baseApi + this.companyId + '/commodity-lots/' + commodityLotId;
    return this.http.get<CommodityLotDto>(path);
  }

  saveCommodityLotFromConsignmentNote(consignmentNote: ConsignmentNoteDto): Observable<number> {
    return this.saveCommodityLot(this.getCommodityLotFromConsignmentNote(consignmentNote));
  }

  getCommodityLotFromConsignmentNote(consignmentNote: ConsignmentNoteDto): CreateCommodityLotDto {
    let commodityLotGoodsDtoArr = consignmentNote.consignmentNoteGoodsList.map((dto) => {
      return new CreateCommodityLotGoodsDto(
        dto.amount,
        dto.goods.id
      )
    });
    return new CreateCommodityLotDto(
      consignmentNote.counterparty.id,
      CommodityLotTypeEnum[consignmentNote.consignmentNoteType],
      commodityLotGoodsDtoArr);
  }

  getCommodityLotFromConsignmentNoteAndWriteOffAct(consignmentNote: ConsignmentNoteDto, createWriteOffActDto: CreateWriteOffActDto): CreateCommodityLotDto {
    createWriteOffActDto.writeOffActGoodsDtoList.forEach((writeOffActGoods) => {
      for (let i = 0; i < consignmentNote.consignmentNoteGoodsList.length; i++) {
        if (writeOffActGoods.goodsId === consignmentNote.consignmentNoteGoodsList[i].goods.id) {
          consignmentNote.consignmentNoteGoodsList[i].amount -= writeOffActGoods.amount;
          break;
        }
      }
    });
    return this.getCommodityLotFromConsignmentNote(consignmentNote);
  }


}
