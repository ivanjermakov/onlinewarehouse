import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {
  MatDialogModule,
  MatInputModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatSelectModule,
  MatSortModule,
  MatTableModule
} from '@angular/material';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {ViewUsersComponent} from './view-users/view-users.component';
import {AppRoutingModule} from './app-routing.module';
import {HomeComponent} from './home/home.component';
import {UserComponent} from './user/user.component';
import {PlacementComponent} from './placement/placement.component';
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


@NgModule({
  declarations: [
    AppComponent,
    ViewUsersComponent,
    HomeComponent,
    UserComponent,
    PlacementComponent,
    PaginationComponent,
    ConsignmentNoteListComponent,
    RegisterConsignmentNoteComponent,
    ConsignmentNoteDetailComponent,
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
    CarrierListViewComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatProgressSpinnerModule,
    MatNativeDateModule,
    MatSelectModule
  ],
  bootstrap: [AppComponent],
  entryComponents: [GoodsListDialogComponent]
})
export class AppModule {
}
