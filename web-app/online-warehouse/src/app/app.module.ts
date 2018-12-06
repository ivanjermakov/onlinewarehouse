import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {MatDialogRef, MatNativeDateModule} from '@angular/material';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AppRoutingModule, routes as appRoutes} from './app-routing.module';
import {HomeComponent} from './home/home.component';
import {CompanyListComponent} from './company/company-list/company-list.component';
import {CreateCompanyComponent} from './company/create-company/create-company.component';
import {GoodsListComponent} from "./shared/goods/goods-list/goods-list.component";
import {WriteOffActListComponent} from './write-off-act/write-off-act-list/write-off-act-list.component';
import {CreateWriteOffActComponent} from './write-off-act/create-write-off-act/create-write-off-act.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {GoodsListDialogComponent} from './shared/goods/goods-list-dialog/goods-list-dialog.component';
import {ConsignmentNoteListComponent} from "./consignment-note/consignment-note-list/consignment-note-list.component";
import {RegisterConsignmentNoteComponent} from "./consignment-note/register-consignment-note/register-consignment-note.component";
import {ConsignmentNoteDetailComponent} from "./consignment-note/consignment-note-list/consignment-note-detail/consignment-note-detail.component";
import {PaginationComponent} from "./shared/pagination/pagination.component";
import {GetWriteOffActComponent} from './write-off-act/get-write-off-act/get-write-off-act.component';
import {CommodityLotListComponent} from './commodity-lot/commodity-lot-list/commodity-lot-list.component';
import {GetCommodityLotComponent} from './commodity-lot/get-commodity-lot/get-commodity-lot.component';
import {CarrierListComponent} from './carrier/carrier-list/carrier-list.component';
import {CarrierListDialogComponent} from './carrier/carrier-list-dialog/carrier-list-dialog.component';
import {GetCarrierComponent} from './carrier/get-carrier/get-carrier.component';
import {CarrierListViewComponent} from './carrier/carrier-list-view/carrier-list-view.component';
import {MaterialModule} from "./material.module";
import {JwtInterceptor} from "./auth/_helpers";
import {AuthenticationService, UserService} from "./auth/_services";
import {AuthGuard} from "./auth/_guards";
import {LoginComponent} from "./auth/login";
import {CounterpartyListComponent} from './counterparty/counterparty-list/counterparty-list.component';
import {CounterpartyListDialogComponent} from './counterparty/counterparty-list-dialog/counterparty-list-dialog.component';
import {CounterpartyListViewComponent} from './counterparty/counterparty-list-view/counterparty-list-view.component';
import {LogoutComponent} from "./auth/login/logout.component";
import {UserListComponent} from './user/user-list/user-list.component';
import {UserListViewComponent} from './user/user-list-view/user-list-view.component';
import {RouterModule, Routes} from "@angular/router";
import {RootComponent} from './root/root.component';
import {MenuComponent} from './menu/menu.component';
import {CreateCounterpartyComponent} from './counterparty/create-counterparty/create-counterparty.component';
import {ChangeBirthdayMailTemplateComponent} from './mail/change-birthday-mail-template/change-birthday-mail-template.component';

import {RegisterComponent} from "./auth/registration/register.component";
import {GetCounterpartyComponent} from './counterparty/get-counterparty/get-counterparty.component';
import {CreateCounterpartyDialogComponent} from './counterparty/create-counterparty-dialog/create-counterparty-dialog.component';
import {CreateCarrierComponent} from './carrier/create-carrier/create-carrier.component';
import {CreateCarrierDialogComponent} from './carrier/create-carrier-dialog/create-carrier-dialog.component';
import {DriverListDialogComponent} from "./carrier/driver/driver-list-dialog/driver-list-dialog.component";
import {DriverListComponent} from "./carrier/driver/driver-list/driver-list.component";
import {WarehouseListComponent} from './warehouse/warehouse-list/warehouse-list.component';
import {CreateWarehouseComponent} from './warehouse/create-warehouse/create-warehouse.component';
import {GetPlacementComponent} from './warehouse/get-placement/get-placement.component';
import {HighchartsChartModule} from "highcharts-angular";
import {DistributeGoodsWarehouseComponent} from './warehouse/distribute-goods-warehouse/distribute-goods-warehouse.component';
import {DistributeGoodsWarehouseDialogComponent} from './warehouse/distribute-goods-warehouse-dialog/distribute-goods-warehouse-dialog.component';
import {ConsignmentNoteDetailDialogComponent} from './consignment-note/consignment-note-list/consignment-note-detail-dialog/consignment-note-detail-dialog.component';
import {CreateWriteOffActDialogComponent} from './write-off-act/create-write-off-act-dialog/create-write-off-act-dialog.component';
import {CreatePlacementComponent} from './warehouse/create-placement/create-placement.component';
import {RegisterConsignmentNoteDialogComponent} from './consignment-note/register-consignment-note-dialog/register-consignment-note-dialog.component';
import {CollectGoodsWarehouseComponent} from './warehouse/collect-goods-warehouse/collect-goods-warehouse.component';
import {PlacementGoodsListDialogComponent} from './warehouse/placement-goods-list-dialog/placement-goods-list-dialog.component';
import {CreateDriverDialogComponent} from './carrier/driver/create-driver-dialog/create-driver-dialog.component';
import {UsernameValidator} from "./user/service/username.validator";
import {GetUserComponent} from './user/get-user/get-user.component';
import {CreateUserComponent} from './user/create-user/create-user.component';
import {ToastrModule} from "ngx-toastr";
import {TOAST_CONFIG} from "./toast-config";
import {CreateGoodsComponent} from './shared/goods/create-goods/create-goods.component';
import {CreateGoodsDialogComponent} from './shared/goods/create-goods-dialog/create-goods-dialog.component';
import {RoleGuardService} from "./auth/_guards/role.guard";

const routes: Routes = [
  {path: 'app', component: AppComponent, canActivate: [AuthGuard], children: appRoutes},
  {path: 'login', component: LoginComponent},
  {path: '**', redirectTo: 'app'}
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CompanyListComponent,
    CreateCompanyComponent,
    GoodsListComponent,
    WriteOffActListComponent,
    CreateWriteOffActComponent,
    GoodsListDialogComponent,
    GetWriteOffActComponent,
    CommodityLotListComponent,
    GetCommodityLotComponent,
    CarrierListComponent,
    CarrierListDialogComponent,
    GetCarrierComponent,
    CarrierListViewComponent,
    LoginComponent,
    CounterpartyListComponent,
    CounterpartyListDialogComponent,
    CounterpartyListViewComponent,
    LogoutComponent,
    UserListComponent,
    UserListViewComponent,
    ConsignmentNoteListComponent,
    ConsignmentNoteDetailComponent,
    RegisterConsignmentNoteComponent,
    PaginationComponent,
    UserListViewComponent,
    RootComponent,
    MenuComponent,
    CreateCounterpartyComponent,
    ChangeBirthdayMailTemplateComponent,
    CreateCounterpartyComponent,
    RegisterComponent,
    GetCounterpartyComponent,
    CreateCounterpartyDialogComponent,
    CreateCarrierComponent,
    CreateCarrierDialogComponent,
    DriverListComponent,
    DriverListDialogComponent,
    CreateCarrierDialogComponent,
    WarehouseListComponent,
    CreateWarehouseComponent,
    GetPlacementComponent,
    DistributeGoodsWarehouseComponent,
    DistributeGoodsWarehouseDialogComponent,
    ConsignmentNoteDetailDialogComponent,
    CreateWriteOffActDialogComponent,
    CreatePlacementComponent,
    RegisterConsignmentNoteDialogComponent,
    CollectGoodsWarehouseComponent,
    PlacementGoodsListDialogComponent,
    CreateDriverDialogComponent,
    GetUserComponent,
    CreateUserComponent,
    CreateGoodsComponent,
    CreateGoodsDialogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatNativeDateModule,
    HighchartsChartModule,
    ToastrModule.forRoot(TOAST_CONFIG)
  ],
  bootstrap: [RootComponent],
  entryComponents: [
    GoodsListDialogComponent,
    CarrierListDialogComponent,
    CounterpartyListDialogComponent,
    CreateCounterpartyDialogComponent,
    CreateCarrierDialogComponent,
    DriverListDialogComponent,
    DistributeGoodsWarehouseDialogComponent,
    ConsignmentNoteDetailDialogComponent,
    CreateWriteOffActDialogComponent,
    RegisterConsignmentNoteDialogComponent,
    PlacementGoodsListDialogComponent,
    CreateDriverDialogComponent,
    CreateGoodsDialogComponent
  ],
  providers: [
    AuthGuard,
    RoleGuardService,
    AuthenticationService,
    UserService,
    UsernameValidator,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },

    // providers used to create fake backend
    // fakeBackendProvider
  ]
})
export class AppModule {
}
