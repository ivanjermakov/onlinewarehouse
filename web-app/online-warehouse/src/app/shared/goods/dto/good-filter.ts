import {PlacementTypeEnum} from "../../enum/placement-type.enum";
import HttpParamsBuilder from "../../http/http-params-builder";

export class GoodFilter {
  name: string;
  placementType: PlacementTypeEnum;
  costFrom: number;
  costTo: number;

  toUrlParameters(paramsBuilder: HttpParamsBuilder): HttpParamsBuilder {
    paramsBuilder.addIfNotEmpty('name', this.name);
    paramsBuilder.addIfNotEmpty('placementType', this.placementType);
    paramsBuilder.addIfNotEmpty('costFrom', this.costFrom);

    return paramsBuilder.addIfNotEmpty('costTo', this.costTo);
  }

  toServerFilter(): GoodFilter {
    let serverFilter = new GoodFilter();
    serverFilter.name = this.name;
    serverFilter.placementType = this.placementType;
    serverFilter.costFrom = this.costFrom;
    serverFilter.costTo = this.costTo;

    return serverFilter;
  }
}
