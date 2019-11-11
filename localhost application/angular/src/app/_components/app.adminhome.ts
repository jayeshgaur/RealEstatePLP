import {Component, OnInit} from '@angular/core';
import { UserModel } from '../_models/app.usermodel';
import { EstateModel } from '../_models/app.estatemodel';
import { EmsService } from '../_service/app.emsservice';
import { Router } from '@angular/router';

@Component({
    selector:'adminhome',
    templateUrl:'../_html/app.adminhome.html'
})
export class AdminHomeComponent implements OnInit{

userList:UserModel[]=[];
estateList:EstateModel[]=[];
userId:any;
estateId:any;
check:any=null;

constructor(private service:EmsService, private router:Router){    
}

ngOnInit(){
    if(!(sessionStorage.getItem('userRole')==="ROLE_Admin")){
        this.router.navigate(['forbidden'])
    }
    this.userId=null;
    this.estateId=null;
    this.estateList=null;
    this.service.getInterestedUsers().subscribe(
        (data:UserModel[]) => {
            this.userList = data;
        }
    );
}

selectUser(user:UserModel){
    if(this.userId == null)
    {
        this.userId=user.userId;
        this.userList=[];
        this.userList.push(user);
        this.service.getMyEstates(user.userId).subscribe((estateList:EstateModel[]) => {
            this.estateList = estateList;
            console.log(this.estateList)
            this.check="a";
        });
    }   
}

changeUser(){
    this.check=null;
    this.ngOnInit();
}

selectEstate(estate:EstateModel){
    this.estateId=estate.estateId;
    this.estateList=[];
    this.estateList.push(estate);
}

confirmOffer(){
    this.service.changeOffer(this.userId,this.estateId).subscribe(
        (data:string)=>{
        this.service.getMyEstates(this.userId).subscribe((testList:EstateModel[]) => this.estateList = testList);
        this.estateId=null;
        alert("Done!")
        }
        ,error => alert("Please refresh the page.")
         
        );}

}