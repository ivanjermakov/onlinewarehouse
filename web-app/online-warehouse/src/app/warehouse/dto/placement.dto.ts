import {PlacementTypeEnum} from "../../shared/enum/placement-type.enum";
import {MeasurementUnitEnum} from "../../shared/enum/measurement-unit.enum";
import {PlacementGoodsDto} from "./placement-goods.dto";

export class PlacementDto {
  id: number;
  warehouseId: number;
  size: number;
  placementType: PlacementTypeEnum;
  measurementUnit: MeasurementUnitEnum;
  storageCost: number;
  placementGoodsList: Array<PlacementGoodsDto>;


  constructor(id?: number, placementGoodsList?: Array<PlacementGoodsDto>, warehouseId?: number, size?: number, placementType?: PlacementTypeEnum,
              measurementUnit?: MeasurementUnitEnum, storageCost?: number) {
    this.id = id;
    this.warehouseId = warehouseId;
    this.size = size;
    this.placementType = placementType;
    this.measurementUnit = measurementUnit;
    this.storageCost = storageCost;
    this.placementGoodsList = placementGoodsList;
  }
}
