import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector:'home',
    templateUrl:'../_html/app.home.html'
})
export class HomeComponent implements OnInit{

    constructor(private router:Router){
        
    }

    ngOnInit(){
        if(sessionStorage.getItem('token')){
            if(sessionStorage.getItem('userRole') === "ROLE_Customer"){
                this.router.navigate(['/userhome'])
            }
            else if(sessionStorage.getItem('userRole') === "ROLE_Admin"){
                this.router.navigate(['/adminhome'])
            }
        }
    }
    
}