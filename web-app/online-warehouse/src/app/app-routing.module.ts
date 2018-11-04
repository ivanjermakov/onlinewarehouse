import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ViewUsersComponent} from './view-users/view-users.component';
import {HomeComponent} from './home/home.component';
import {UserComponent} from './user/user.component';
import {CompanyListComponent} from "./company/company-list/company-list.component";
import {CreateCompanyComponent} from "./company/create-company/create-company.component";
import {GoodsListComponent} from "./shared/goods/goods-list/goods-list.component";
import {WriteOffActListComponent} from "./write-off-act/write-off-act-list/write-off-act-list.component";
import {CreateWriteOffActComponent} from "./write-off-act/create-write-off-act/create-write-off-act.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'view-users', component: ViewUsersComponent},
  {path: 'user/:id', component: UserComponent},
  {path: 'user', component: UserComponent},
  {path: 'list-companies', component: CompanyListComponent},
  {path: 'create-company', component: CreateCompanyComponent},
  {path: 'test', component: GoodsListComponent},
  {path: 'list-write-off-acts', component: WriteOffActListComponent},
  {path: 'create-write-off-act', component: CreateWriteOffActComponent},
  // { path: 'heroes', component:  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule {
}
