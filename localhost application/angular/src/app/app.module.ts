import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import{FormsModule} from '@angular/forms';
import { Routes, RouterModule } from '@angular/router'
import { TestComponent } from './_components/app.test';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HomeComponent } from './_components/app.home';
import { RegisterComponent } from './_components/app.register';
import { LoginComponent } from './_components/app.login';
import { AdminHomeComponent } from './_components/app.adminhome';
import { UserHomeComponent } from './_components/app.userhome';
import { LogoutComponent } from './_components/app.logout';
import {NgxPaginationModule} from 'ngx-pagination'; 
import { Error403Component } from './_components/app.error403';
import { AddEstateComponent } from './_components/app.addestate';
import { BasicAuthHtppInterceptorService } from './_service/app.basicauthservice';
import { Error404Component } from './_components/app.error404';

const myRoute: Routes =[
    { path: '', redirectTo:"home",pathMatch:'full'},
    { path: 'home', component:HomeComponent},
    { path: 'register', component:RegisterComponent},
    { path: 'login', component:LoginComponent},
    { path: 'adminhome', component:AdminHomeComponent},
    { path: 'userhome', component:UserHomeComponent},
    { path: 'logout', component:LogoutComponent},
    { path: 'test', component:TestComponent},
    { path: 'forbidden', component:Error403Component},
    { path: 'addestate', component:AddEstateComponent},
    { path: '**', component:Error404Component}
    
  

]

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot(myRoute),
        NgxPaginationModule
        
    ],
    declarations: [
        AppComponent, Error403Component, TestComponent, 
        HomeComponent, RegisterComponent, LoginComponent,
         AdminHomeComponent, UserHomeComponent, LogoutComponent,
         AddEstateComponent, Error404Component
       	],
    providers: [  {  
        provide:HTTP_INTERCEPTORS, useClass:BasicAuthHtppInterceptorService, multi:true 
      }],
    bootstrap: [AppComponent]
})

export class AppModule { }