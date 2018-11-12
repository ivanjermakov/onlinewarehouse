import {PlacementGoodsDto} from "../../placement/dto/placement-goods.dto";

export class WarehouseDto {
  id: number;
  name: string;
  placements: Array<PlacementGoodsDto>;
}
