import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import {AuthGuard} from "./auth.guard";
import {JwtHelperService} from "@auth0/angular-jwt";
import {AuthenticationService} from "../_services";
import {ToastrService} from "ngx-toastr";

@Injectable()
export class RoleGuardService implements CanActivate {

  private jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private authGuard: AuthGuard,
              private router: Router,
              private auth: AuthenticationService,
              private toastr: ToastrService) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {

    const expectedRole = route.data.expectedRole;

    if (
      !this.authGuard.isAuthenticated() ||
      !this.auth.getAuthorities().some(authority => {
        return expectedRole.some(role =>{
          return authority.authority === role;
        })
      })
    ) {
      this.toastr.error('Request has not been applied because it lacks valid authentication credentials for ' +
        'the target resource.', 'Unauthorized');
      this.router.navigate(['/login']);
      return false;
    }
    return true;
  }
}
