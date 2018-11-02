import {PlacementTypeEnum} from "../enum/placement-type.enum";
import {MeasurementUnitEnum} from "../enum/measurement-unit.enum";

export class GoodsDto {
  constructor(
    public id: number,
    public name: string,
    public  placementType: PlacementTypeEnum,
    public measurementUnit: MeasurementUnitEnum,
    public cost: number,
    public weight: number,
    public labelling: string,
    public description: string
  ) {

  }

}
