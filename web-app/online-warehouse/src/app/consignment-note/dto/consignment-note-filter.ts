import HttpParamsBuilder from "../../shared/http/http-params-builder";

export class ConsignmentNoteFilter {
  consignmentNoteType: string = '';
  consignmentNoteStatus: string = '';
  from: string = '';
  to: string = '';

  toUrlParameters(paramsBuilder: HttpParamsBuilder): HttpParamsBuilder {
    paramsBuilder.addIfNotEmpty('consignmentNoteType', this.consignmentNoteType);
    paramsBuilder.addIfNotEmpty('consignmentNoteStatus', this.consignmentNoteStatus);
    paramsBuilder.addIfNotEmpty('from', this.from);

    return paramsBuilder.addIfNotEmpty('to', this.to);
  }
}
