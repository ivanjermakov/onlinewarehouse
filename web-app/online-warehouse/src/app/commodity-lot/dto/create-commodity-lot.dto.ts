import {CommodityLotTypeEnum} from "./enum/commodity-lot-type.enum";
import {CreateCommodityLotGoodsDto} from "./create-commodity-lot-goods.dto";

export class CreateCommodityLotDto {
  counterpartyId: number;
  commodityLotType: CommodityLotTypeEnum;
  createCommodityLotGoodsDtoList: Array<CreateCommodityLotGoodsDto>;


  constructor(counterpartyId: number, commodityLotType: CommodityLotTypeEnum, createCommodityLotGoodsDtoList: Array<CreateCommodityLotGoodsDto>) {
    this.counterpartyId = counterpartyId;
    this.commodityLotType = commodityLotType;
    this.createCommodityLotGoodsDtoList = createCommodityLotGoodsDtoList;
  }
}
