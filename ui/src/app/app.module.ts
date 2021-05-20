import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Table1Component } from './table1/table1.component';
import { MatchtableComponent } from './matchtable/matchtable.component';

import{ HttpClientModule } from '@angular/common/http';

import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [
    
    Table1Component,
    MatchtableComponent,
    AppComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
