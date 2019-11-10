import {Component} from '@angular/core';
import { EmsService } from '../_service/app.emsservice';

@Component({
    selector:'test',
    templateUrl:'../_html/app.test.html'
})
export class TestComponent{

  imageBlobUrl: string = null;
  
    constructor(private service:EmsService){
        this.getThumbnail();
    }
  
    getThumbnail() : void {
    // this.service.getBlobThumbnail()
    //   .subscribe((val) => {
    //       console.log(val);
    //       this.createImageFromBlob(val);
    //     });
  }

  createImageFromBlob(image: Blob) {
    let reader = new FileReader();
    reader.addEventListener("load", () => {
      this.imageBlobUrl = reader.result.toString();
    }, false);
    if (image) {
      reader.readAsDataURL(image);
    }
  }
}
