import {Component, OnInit} from '@angular/core';
import { EstateModel } from '../_models/app.estatemodel';
import { EmsService } from '../_service/app.emsservice';
import { Router } from '@angular/router';
import { saveAs } from 'file-saver';
import { element } from '@angular/core/src/render3/instructions';
import { Subscriber } from 'rxjs';

@Component({
    selector:'userhome',
    templateUrl:'../_html/app.userhome.html'
})
export class UserHomeComponent implements OnInit{
    
    estateList:EstateModel[]=[];
    searchByCity:any;
    searchByArea:any;
    sortByPrice:any;

    constructor(private service:EmsService, private router:Router){
        this.getEstates();
    }

    ngOnInit(){

         if(!(sessionStorage.getItem('userRole') === "ROLE_Customer")){
                this.router.navigate(['forbidden']);
            }
        
       

     }

     getEstates(){
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

      sortEstates(){

        this.service.getEstates().subscribe((data:EstateModel[])=>{
            this.estateList=data;
            this.estateList.forEach(element => {
                this.service.getBlobThumbnail(element.imageList[0].url)
                .subscribe((val) => {
                    this.createImageFromBlob(element.estateId,val);


                    if(this.searchByArea == 1){
                        console.log("Inside ==1")
          
                      for(let i=this.estateList.length-1;i>=0 ;i--){
                         
                          if(this.estateList[i].estateArea > 300 || this.estateList[i].estateArea < 100 ){
                            this.estateList.splice(i,1);
                          }
                        }
                    
                    }else if(this.searchByArea == 2){
                      console.log("Inside ==2")
             
                      for(let i=this.estateList.length-1;i>=0 ;i--){
                          if(this.estateList[i].estateArea > 550 || this.estateList[i].estateArea < 301 ){
                            this.estateList.splice(i,1);
                          }
                        }
                        
                    }else if(this.searchByArea == 3){
                      console.log("Inside ==3")
                 
                      for(let i=this.estateList.length-1;i>=0 ;i--){
                          if(this.estateList[i].estateArea < 550 ){
                            this.estateList.splice(i,1);
                          }
                        }
          
                    }else{
                      
                    }
          
                    if(this.searchByCity == "Mumbai"){
                        console.log("inside mumbai");
                      for(let i=this.estateList.length-1;i>=0 ;i--){
                          if(this.estateList[i].estateAddress.city !== "Mumbai" ){
                            this.estateList.splice(i,1);
                          }
                        }
          
                    }else if(this.searchByCity == "Delhi"){
                      console.log("inside Delhi");
                      for(let i=this.estateList.length-1;i>=0 ;i--){
                          if(this.estateList[i].estateAddress.city !== "Delhi" ){
                            this.estateList.splice(i,1);
                          }
                        }
          
                    }else if(this.searchByCity == "Kolkata"){
                      console.log("inside Kolkata");
                      for(let i=this.estateList.length-1;i>=0 ;i--){
                          if(this.estateList[i].estateAddress.city !== "Kolkata" ){
                            this.estateList.splice(i,1);
                          }
                        }
          
                    }else{ 
                  }
                       

                  }); 
            });
          });


      }

      sortPrice(){
        if (this.sortByPrice != 1) {
            this.estateList.sort((left, right) => left.estatePrice > right.estatePrice ? 1 : -1 );
          }
          else if (this.sortByPrice == 1){
            this.estateList.sort((right, left) => left.estatePrice > right.estatePrice ? 1 : -1 );
          }

      }

      downloadBrochure(estateId:any){
       this.service.download(estateId).subscribe(
            response => {
              var blob = new Blob([response], {type: 'application/pdf'});
              var filename = 'Brochure.pdf';
              saveAs(blob,filename);  
            }
       );
      }
    
}