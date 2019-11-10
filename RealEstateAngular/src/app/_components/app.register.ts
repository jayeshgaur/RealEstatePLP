import {Component, OnInit} from '@angular/core';
import { UserModel } from '../_models/app.usermodel';
import { EmsService } from '../_service/app.emsservice';
import { Router } from '@angular/router';
import { e, E } from '@angular/core/src/render3';
@Component({
    selector:'register',
    templateUrl:'../_html/app.register.html'
})
export class RegisterComponent implements OnInit{

    date = new Date();  
    maxDate = (new Date().getFullYear()-5).toString()+"-"+(new Date().getMonth()+1).toString()+"-"+(new Date().getDate()).toString();


    constructor(private service:EmsService, private router:Router){
        console.log(this.maxDate)
    }

    ngOnInit() {
       
    }

    user:any={userName:"", userPassword:"", contactNo:"", userEmail:"", age:"", gender:""};
    errorMessage:any;

    checkDate(){
        if( this.maxDate < this.user.birthDate){
            this.errorMessage="You need to be older than 5 years of age";
        }
        else{
            this.errorMessage="";
        }
    }

    register(){

       console.log(this.user.birthDate);

        if( this.maxDate < this.user.birthDate){
            this.errorMessage="You need to be older than 5 years of age";
        }
        else{
        this.service.register(this.user). subscribe (
            (data: UserModel)=>{alert("Registration successful. Please login, "+data.userName)
            this.router.navigate(['/login']);
            }, 
            error => alert(error.error)
            );
        }
    }

    dateChange(event){
        console.log(event);
      }

}