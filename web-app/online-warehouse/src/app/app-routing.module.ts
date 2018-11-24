import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './home/home.component';
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
import {UserListViewComponent} from "./user/user-list-view/user-list-view.component";
import {CreateCounterpartyComponent} from "./counterparty/create-counterparty/create-counterparty.component";
import {RegisterComponent} from "./auth/registration/register.component";
import {GetCounterpartyComponent} from "./counterparty/get-counterparty/get-counterparty.component";
import {CreateCarrierComponent} from "./carrier/create-carrier/create-carrier.component";
import {WarehouseListComponent} from "./warehouse/warehouse-list/warehouse-list.component";
import {CreateWarehouseComponent} from "./warehouse/create-warehouse/create-warehouse.component";
import {GetPlacementComponent} from "./warehouse/get-placement/get-placement.component";
import {DistributeGoodsWarehouseComponent} from "./warehouse/distribute-goods-warehouse/distribute-goods-warehouse.component";
import {CreatePlacementComponent} from "./warehouse/create-placement/create-placement.component";

export const routes: Routes = [
  {path: 'list-users', component: UserListViewComponent, canActivate: [AuthGuard]},
  // {path: 'user/:id', component: UserComponent, canActivate: [AuthGuard]},
  // {path: 'user', component: UserComponent, canActivate: [AuthGuard]},
  {path: 'list-companies', component: CompanyListComponent, canActivate: [AuthGuard]},
  {path: 'create-company', component: CreateCompanyComponent, canActivate: [AuthGuard]},
  {path: 'list-goods', component: GoodsListComponent, canActivate: [AuthGuard]},
  {path: 'list-write-off-acts', component: WriteOffActListComponent, canActivate: [AuthGuard]},
  {path: 'create-write-off-act', component: CreateWriteOffActComponent, canActivate: [AuthGuard]},
  {path: 'write-off-act/:id', component: GetWriteOffActComponent, canActivate: [AuthGuard]},
  {path: 'list-commodity-lots', component: CommodityLotListComponent, canActivate: [AuthGuard]},
  {path: 'commodity-lot/:id', component: GetCommodityLotComponent, canActivate: [AuthGuard]},
  {path: 'list-carriers', component: CarrierListViewComponent, canActivate: [AuthGuard]},
  {path: 'carrier/:id', component: GetCarrierComponent, canActivate: [AuthGuard]},
  {path: 'create-carrier', component: CreateCarrierComponent, canActivate: [AuthGuard]},
  {path: 'list-counterparties', component: CounterpartyListViewComponent, canActivate: [AuthGuard]},
  {path: 'counterparty/:id', component: GetCounterpartyComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'register-consignment-note', component: RegisterConsignmentNoteComponent, canActivate: [AuthGuard]},
  {path: 'register-consignment-note/:id', component: RegisterConsignmentNoteComponent, canActivate: [AuthGuard]},
  {path: 'consignment-notes', component: ConsignmentNoteListComponent, canActivate: [AuthGuard]},
  {path: 'registered-consignment-notes', component: ConsignmentNoteListComponent, canActivate: [AuthGuard]},
  {path: 'consignment-notes/:id', component: ConsignmentNoteDetailComponent, canActivate: [AuthGuard]},
  {path: 'create-counterparty', component: CreateCounterpartyComponent, canActivate: [AuthGuard]},
  {path: 'register', component: RegisterComponent, canActivate: [AuthGuard]},
  {path: 'list-warehouse', component: WarehouseListComponent, canActivate: [AuthGuard]},
  {path: 'create-warehouse', component: CreateWarehouseComponent, canActivate: [AuthGuard]},
  {path: 'placement/:warehouseId/:placementId', component: GetPlacementComponent, canActivate: [AuthGuard]},
  {path: 'distribute-goods', component: DistributeGoodsWarehouseComponent, canActivate: [AuthGuard]},
  {path: 'registered-commodity-lots', component: CommodityLotListComponent, canActivate: [AuthGuard]},
  {path: 'warehouse/:id/create-placement', component: CreatePlacementComponent, canActivate: [AuthGuard]},

  // { path: 'heroes', component:  }
];

@NgModule({
  imports: [RouterModule],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule {
}
