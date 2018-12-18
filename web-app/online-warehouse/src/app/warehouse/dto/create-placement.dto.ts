import {PlacementTypeEnum} from "../../shared/enum/placement-type.enum";
import {MeasurementUnitEnum} from "../../shared/enum/measurement-unit.enum";

export class CreatePlacementDto {

  warehouseId: number;
  size: number;
  placementType: PlacementTypeEnum;
  measurementUnit: MeasurementUnitEnum;
  storageCost: number;

}
