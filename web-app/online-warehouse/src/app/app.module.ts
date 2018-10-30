import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {ViewUsersComponent} from './view-users/view-users.component';
import {AppRoutingModule} from './app-routing.module';
import {HomeComponent} from './home/home.component';
import {UserComponent} from './user/user.component';
import { PlacementComponent } from './placement/placement.component';

export const API_BASE_URL = 'http://localhost:8080';

@NgModule({
  declarations: [
    AppComponent,
    ViewUsersComponent,
    HomeComponent,
    UserComponent,
    PlacementComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [{provide: API_BASE_URL, useValue: 'localhost:8080'}],
  bootstrap: [AppComponent]
})
export class AppModule {
}
