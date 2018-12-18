import {Injectable} from '@angular/core';
import {ToastrService} from "ngx-toastr";

@Injectable({
  providedIn: 'root'
})
export class RequestErrorToastHandlerService {

  constructor(private toastr: ToastrService) {
  }

  handleError(error) {
    if (error.status == 0) {
      this.toastr.error('No response from server.', 'Сonnection refused')
    } else if (error.status == 404) {
      this.toastr.error('The server can not find requested resource.', 'Not Found')
    } else if (error.status == 401) {
      this.toastr.error('Request has not been applied because it lacks valid authentication credentials for the target resource.', 'Unauthorized')
    } else if (error.status == 400) {
      this.toastr.error('Please check the submitted data.', 'Bad request')
    } else if (error.status == 500) {
      this.toastr.error('The server has encountered a situation it doesn\'t know how to handle.', 'Internal Server Error')
    } else {
      this.toastr.error('Something went wrong.', 'Error')
    }
  }

  handleInfo(message: string, title: string) {
    this.toastr.info(message, title);
  }

  handleSuccess(message: string, title: string) {
    this.toastr.success(message, title);
  }

  handleCustomError(message: string, title: string) {
    this.toastr.error(message, title);
  }
}
