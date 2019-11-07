import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import{FormsModule} from '@angular/forms';
import { Routes, RouterModule } from '@angular/router'
import { TestComponent } from './_components/app.test';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './_components/app.home';
import { RegisterComponent } from './_components/app.register';
const myRoute: Routes =[
    { path: '', redirectTo:"home",pathMatch:'full'},
    { path: 'test', component:TestComponent},
    { path: 'register', component:RegisterComponent},
    { path: 'home', component:HomeComponent}
]

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot(myRoute),
        
    ],
    declarations: [
        AppComponent, TestComponent, HomeComponent, RegisterComponent
       	],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }