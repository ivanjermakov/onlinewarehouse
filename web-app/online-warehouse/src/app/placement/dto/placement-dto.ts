import {PlacementGoodsDto} from "./placement-goods.dto";

class PlacementDto {
  id: number;
  warehouseId: number;
  size: number;
  placementType: string;
  measurementUnit: string;
  storageCost: number;
  placementGoodsList: Array<PlacementGoodsDto>;
}
