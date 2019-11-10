import {Component, OnInit} from '@angular/core';
import { EstateModel } from '../_models/app.estatemodel';
import { EmsService } from '../_service/app.emsservice';
import { Router } from '@angular/router';

@Component({
    selector:'userhome',
    templateUrl:'../_html/app.userhome.html'
})
export class UserHomeComponent implements OnInit{
    
    estateList:EstateModel[]=[];
 
    constructor(private service:EmsService, private router:Router){

    }

    ngOnInit(){

         if(!(sessionStorage.getItem('userRole') === "ROLE_Customer")){
                this.router.navigate(['forbidden']);
            }
        

        this.service.getEstates().subscribe((data:EstateModel[])=>{
            this.estateList=data;
            this.estateList.forEach(element => {
                this.service.getBlobThumbnail(element.imageList[0].url)
                .subscribe((val) => {
                    console.log(val);
                    this.createImageFromBlob(element.estateId,val);
                  }); 
            });
          });


       
      

     }
    
     createImageFromBlob(estateId:any, image: Blob) {
        let reader = new FileReader();
        reader.addEventListener("load", () => {

            this.estateList.forEach(element =>{
                    if(element.estateId == estateId){
                        element.thumbnail = reader.result.toString();
                    }
            });

        }, false);
        if (image) {
          reader.readAsDataURL(image);
        }
      }

      changeThumbnail(url:any, estateId:any){
          this.estateList.forEach(element => {
                if(element.estateId == estateId){
                    element.thumbnail = url;
                }
          });
      }
    
}