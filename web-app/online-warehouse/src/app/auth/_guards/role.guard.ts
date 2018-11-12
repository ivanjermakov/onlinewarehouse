import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import {AuthGuard} from "./auth.guard";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable()
export class RoleGuard implements CanActivate {

  private jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private authGuard: AuthGuard,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config
    // on the data property
    const expectedRole = route.data.expectedRole;
    const token = localStorage.getItem('token');
    // decode the token to get its payload
    const tokenPayload = this.jwtHelper.decodeToken(token);
    if (
      !this.authGuard.isAuthenticated() ||
      tokenPayload.role !== expectedRole
    ) {
      this.router.navigate(['login']);
      return false;
    }
    return true;
  }
}
