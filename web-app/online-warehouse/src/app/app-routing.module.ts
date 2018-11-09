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
import {GetWriteOffActComponent} from "./write-off-act/get-write-off-act/get-write-off-act.component";
import {CommodityLotListComponent} from "./commodity-lot/commodity-lot-list/commodity-lot-list.component";
import {GetCommodityLotComponent} from "./commodity-lot/get-commodity-lot/get-commodity-lot.component";
import {GetCarrierComponent} from "./carrier/get-carrier/get-carrier.component";
import {CarrierListViewComponent} from "./carrier/carrier-list-view/carrier-list-view.component";
import {RegisterConsignmentNoteComponent} from "./consignment-note/register-consignment-note/register-consignment-note.component";
import {ConsignmentNoteListComponent} from "./consignment-note/consignment-note-list/consignment-note-list.component";
import {ConsignmentNoteDetailComponent} from "./consignment-note/consignment-note-list/consignment-note-detail/consignment-note-detail.component";
import {LoginComponent} from "./auth/login";
import {AuthGuard} from "./auth/_guards";
import {CounterpartyListViewComponent} from "./counterparty/counterparty-list-view/counterparty-list-view.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'view-users', component: ViewUsersComponent},
  {path: 'user/:id', component: UserComponent},
  {path: 'user', component: UserComponent},
  {path: 'list-companies', component: CompanyListComponent},
  {path: 'create-company', component: CreateCompanyComponent},
  {path: 'list-goods', component: GoodsListComponent},
  {path: 'list-write-off-acts', component: WriteOffActListComponent},
  {path: 'create-write-off-act', component: CreateWriteOffActComponent},
  {path: 'write-off-act/:id', component: GetWriteOffActComponent},
  {path: 'list-commodity-lots', component: CommodityLotListComponent},
  {path: 'commodity-lot/:id', component: GetCommodityLotComponent},
  {path: 'list-carriers', component: CarrierListViewComponent},
  {path: 'carrier/:id', component: GetCarrierComponent},
  {path: 'list-counterparties', component: CounterpartyListViewComponent, canActivate: [AuthGuard]},
  // {path: 'counterparty/:id', component: GetCarrierComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'register-consignment-note', component: RegisterConsignmentNoteComponent},
  {path: 'consignment-notes', component: ConsignmentNoteListComponent},
  {path: 'consignment-notes/:id', component: ConsignmentNoteDetailComponent},
  // { path: 'heroes', component:  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule {
}
