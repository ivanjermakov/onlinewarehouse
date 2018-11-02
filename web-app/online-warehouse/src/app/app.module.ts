import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {ViewUsersComponent} from './view-users/view-users.component';
import {AppRoutingModule} from './app-routing.module';
import {HomeComponent} from './home/home.component';
import {UserComponent} from './user/user.component';
import {PlacementComponent} from './placement/placement.component';
import {ConsignmentNoteComponent} from './consignment-note/consignment-note.component';
import {CompanyListComponent} from './company/company-list/company-list.component';
import {CreateCompanyComponent} from './company/create-company/create-company.component';
import {GoodsListComponent} from "./shared/goods/goods-list/goods-list.component";


@NgModule({
  declarations: [
    AppComponent,
    ViewUsersComponent,
    HomeComponent,
    UserComponent,
    PlacementComponent,
    ConsignmentNoteComponent,
    CompanyListComponent,
    CreateCompanyComponent,
    GoodsListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
