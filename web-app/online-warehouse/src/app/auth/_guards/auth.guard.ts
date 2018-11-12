import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable()
export class AuthGuard implements CanActivate {

  private jwtHelper: JwtHelperService = new JwtHelperService();

  constructor(private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (localStorage.getItem('currentUser')) {
      // logged in so return true
      if (!this.jwtHelper.isTokenExpired(localStorage.getItem('currentUser')))
        return true;
    }

    // not logged in so redirect to login page
    this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
    return false;
  }

  public isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    // Check whether the token is expired and return
    // true or false
    return !this.jwtHelper.isTokenExpired(token);
  }
}
