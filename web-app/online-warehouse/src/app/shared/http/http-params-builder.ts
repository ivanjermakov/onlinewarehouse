import {HttpParams} from "@angular/common/http";
import {CustomEncoder} from "./custom-encoder";

export default class HttpParamsBuilder {
  private httpParams: HttpParams = new HttpParams({encoder: new CustomEncoder()});

  set(paramName: string, paramValue: string): HttpParamsBuilder {
    this.httpParams = this.httpParams.set(paramName, paramValue);

    return this;
  }

  append(paramName: string, paramValue: string): HttpParamsBuilder {
    this.httpParams = this.httpParams.append(paramName, paramValue);

    return this;
  }

  /**
   * Add a param if its value in not empty (including null)
   * @param {HttpParams} params
   * @param {string} field
   * @param value
   * @returns {string}
   */
  addIfNotEmpty(field: string, value: any): HttpParamsBuilder {
    if (value != null) {
      if (value instanceof Array) {
        value.forEach(item => {
          let textValue = '' + value;
          if (textValue) {
            this.append(field, item)
          }
        });
      } else {
        let textValue = '' + value;
        if (textValue) {
          this.set(field, textValue);
        }
      }
    }
    return this;
  }

  getHttpParams(): HttpParams {
    return this.httpParams;
  }
}
