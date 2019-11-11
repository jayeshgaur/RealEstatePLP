import {Component, OnInit} from '@angular/core';
import { AddressModel } from '../_models/app.addressmodel';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';


@Component({
    selector:'addestate',
    templateUrl:'../_html/app.addestate.html'
})
export class AddEstateComponent implements OnInit{
   
    estateAddress:any={addressLine:"", city:"", state:"", pincode:""};
    estate:any={estateName:"", estateArea:"", estatePrice:""};
    myFiles:string [] = [];
    errorMessage:any;

    constructor(private router:Router, private myhttp:HttpClient){

    }

    ngOnInit(){
        if(!(sessionStorage.getItem('userRole') === "ROLE_Customer")){
            this.router.navigate(['forbidden']);
        }
    }

    getFileDetails (e) {
        for (var i = 0; i < e.target.files.length; i++) { 
          this.myFiles.push(e.target.files[i]);
        }
      }

      addProperty(){
          console.log(sessionStorage.getItem('userId'))
          if(this.myFiles.length === 5){
            const frmData = new FormData();
            for (var i = 0; i < this.myFiles.length; i++) {
                frmData.append("files", this.myFiles[i]);
              }
              frmData.append("estateName", this.estate.estateName);
              frmData.append("estateArea", this.estate.estateArea);
              frmData.append("estatePrice", this.estate.estatePrice);
              frmData.append("estateAddress.addressLine", this.estateAddress.addressLine);
              frmData.append("estateAddress.city", this.estateAddress.city);
              frmData.append("estateAddress.state", this.estateAddress.state);
              frmData.append("estateAddress.pincode", this.estateAddress.pincode);
              frmData.append("userId", sessionStorage.getItem('userId'));

              this.myhttp.post('http://localhost:9123/addProperty', frmData).subscribe(
                  data => {
                    alert("Added successfully!")
                    location.reload();
                  }
              );

          }else{
            this.errorMessage="Select 5 images to upload!";
          }
      }

}