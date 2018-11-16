import HttpParamsBuilder from "../../shared/http/http-params-builder";

export class UserFilter {
  firstname: string;
  lastname: string;

  toUrlParameters(paramsBuilder: HttpParamsBuilder): HttpParamsBuilder {
    paramsBuilder.addIfNotEmpty('firstname', this.firstname);

    return paramsBuilder.addIfNotEmpty('lastname', this.lastname);
  }

  toServerFilter(): UserFilter {
    let serverFilter = new UserFilter();
    serverFilter.firstname = this.firstname;
    serverFilter.lastname = this.lastname;

    return serverFilter;
  }
}
