import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent }  from './app.component';
import{FormsModule} from '@angular/forms';
import { Routes, RouterModule } from '@angular/router'
import { TestComponent } from './_components/app.test';
import { HttpClientModule } from '@angular/common/http';
const myRoute: Routes =[
    { path: 'test', component:TestComponent}
]

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpClientModule,
        RouterModule.forRoot(myRoute),
        
    ],
    declarations: [
        AppComponent, TestComponent
   	],
    providers: [ ],
    bootstrap: [AppComponent]
})

export class AppModule { }