import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import{FormsModule} from '@angular/forms';
import { Routes, RouterModule } from '@angular/router'
import { TestComponent } from './_components/app.test';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './_components/app.home';
import { RegisterComponent } from './_components/app.register';
import { LoginComponent } from './_components/app.login';
import { AdminHomeComponent } from './_components/app.adminhome';
import { UserHomeComponent } from './_components/app.userhome';
import { LogoutComponent } from './_components/app.logout';
import { Error403Component } from './_components/app.error403';
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
    { path: '**', component:HomeComponent}
    
  

]

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot(myRoute),
        
    ],
    declarations: [
        AppComponent, Error403Component, TestComponent, HomeComponent, RegisterComponent, LoginComponent, AdminHomeComponent, UserHomeComponent, LogoutComponent
       	],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }