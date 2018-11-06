import HttpParamsBuilder from "../../shared/http/http-params-builder";

export class ConsignmentNoteFilter {
  consignmentNoteType: string;
  consignmentNoteStatus: string;
  from: string;
  to: string;

  toUrlParameters(paramsBuilder: HttpParamsBuilder): HttpParamsBuilder {
    paramsBuilder.addIfNotEmpty('consignmentNoteType', this.consignmentNoteType);
    paramsBuilder.addIfNotEmpty('consignmentNoteStatus', this.consignmentNoteStatus);

    return paramsBuilder.addIfNotEmpty('from', this.from);
  }

  toServerFilter(): ConsignmentNoteFilter {
    let serverFilter = new ConsignmentNoteFilter();
    serverFilter.consignmentNoteType = this.consignmentNoteType;
    serverFilter.consignmentNoteStatus = this.consignmentNoteStatus;
    serverFilter.from = this.from;
    serverFilter.to = this.to;

    return serverFilter;
  }
}
