import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ViewUsersComponent} from './view-users/view-users.component';
import {HomeComponent} from './home/home.component';
import {UserComponent} from './user/user.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'view-users', component: ViewUsersComponent},
  { path: 'user/:id', component: UserComponent},
  { path: 'user', component: UserComponent},
  // { path: 'heroes', component:  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ],
  declarations: []
})
export class AppRoutingModule { }
