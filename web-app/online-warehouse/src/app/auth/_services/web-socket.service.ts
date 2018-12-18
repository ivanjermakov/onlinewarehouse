import {Injectable} from '@angular/core';
import {AuthenticationService} from "./authentication.service";
import {Stomp} from "@stomp/stompjs";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {API_BASE_URL} from "../../base-server-url";

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  ws: any;
  name: string;
  private baseApi: string = `ws://${API_BASE_URL.split('://')[1]}/websocket/websocket`;

  constructor(private auth: AuthenticationService,
              private errorToast: RequestErrorToastHandlerService) {
  }

  connect() {
    let socket = new WebSocket(this.baseApi);
    this.ws = Stomp.over(socket);
    this.ws.debug = () => {
    };
    let that = this;
    let authorities = this.auth.getAuthorities().map((auth) => {
      return auth.authority;
    });
    this.ws.connect({}, (frame) => {
      that.ws.subscribe("/errors", (message) => {
        that.errorToast.handleCustomError(message, "WebSocket error");
      });
      if (authorities.includes('ROLE_DISPATCHER')) {
        that.ws.subscribe(`/company/${that.auth.getCompanyId()}/dispatcher/commodity-lot`, (message) => { //New commodity lot has been added
          that.errorToast.handleInfo(message.body, "Info");
        });
      }
      if (authorities.includes('ROLE_MANAGER')) {
        that.ws.subscribe(`/company/${that.auth.getCompanyId()}/manager/warehouse`, (message) => { //New warehouse has been added
          that.errorToast.handleInfo(message.body, "Info");
        });
        that.ws.subscribe(`/company/${that.auth.getCompanyId()}/manager/write-off-case`, (message) => { //New write-off case has been added
          that.errorToast.handleInfo(message.body, "Info");
        });
        that.ws.subscribe(`/company/${that.auth.getCompanyId()}/manager/commodity-lot`, (message) => { //New commodity lot to process has been added
          that.errorToast.handleInfo(message.body, "Info");
        });
        that.ws.subscribe(`/company/${that.auth.getCompanyId()}/manager/commodity-lot-processed`, (message) => { //Commodity lot has been shipped
          that.errorToast.handleInfo(message.body, "Info");
        });
        that.ws.subscribe(`/company/${that.auth.getCompanyId()}/manager/consignment-note`, (message) => { //New consignment note has been added
          that.errorToast.handleInfo(message.body, "Info");
        });
      }
      if (authorities.includes('ROLE_INSPECTOR')) {
        that.ws.subscribe(`/company/${that.auth.getCompanyId()}/inspector/consignment-note`, (message) => { //New consignment note has been added
          that.errorToast.handleInfo(message.body, "Info");
        });
      }
    }, (error) => {
      that.errorToast.handleError(error);
    });
  }

  disconnect() {
    if (this.ws != null) {
      this.ws.ws.close();
    }
  }
}


