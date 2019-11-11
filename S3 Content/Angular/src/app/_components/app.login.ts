import {Component, OnInit} from '@angular/core';
import { UserModel } from '../_models/app.usermodel';
import { EmsService } from '../_service/app.emsservice';
import { Router } from '@angular/router';
import { e, E } from '@angular/core/src/render3';
@Component({
    selector:'login',
    templateUrl:'../_html/app.login.html'
})
export class LoginComponent implements OnInit{
    model: any = [];
    useremail: any;
    password: any;
    invalidLogin = false;

    constructor(private service:EmsService, private router:Router){
       
    }

    ngOnInit() {
       
    }

    checkLogin() {
        console.log("Inside login.ts checkLogin.. email: " + this.useremail + " password: " + this.password)
        this.service.authenticate(this.useremail, this.password).subscribe(
          userData => {
            sessionStorage.setItem('username', this.useremail);
            let tokenStr = 'Bearer ' + userData.token;
            sessionStorage.setItem('token', tokenStr);
            this.service.getUser(this.useremail).subscribe((data: UserModel) => {
              this.model = data;
              sessionStorage.setItem('userRole', data.userRole);
              sessionStorage.setItem('userId', data.userId);
              sessionStorage.setItem('userName', data.userName)
              this.checkRoles();
            });
          },
          error => {
            alert("Invalid Credentials  ")
          });
        }
    
    
      checkRoles(){
    
        if (sessionStorage.getItem('userRole') === "ROLE_Customer") {
          this.router.navigate(['/userhome']).then(()=>{window.location.reload();});
        } 
        else if(sessionStorage.getItem('userRole') === "ROLE_Admin"){
          this.router.navigate(['/adminhome']).then(()=>{window.location.reload();});
        }
        
      }
   

}