import {Component} from '@angular/core';
import { EmsService } from '../_service/app.emsservice';

@Component({
    selector:'test',
    templateUrl:'../_html/app.test.html'
})
export class TestComponent{
    constructor(private service:EmsService){
        this.getThumbnail();
    }
    imageBlobUrl: string | null = null;
getThumbnail() : void {
    this.service.getBlobThumbnail()
      .subscribe((val) => {
          console.log(val);
          this.createImageFromBlob(val);
        },
        response => {
          console.log("POST - getThumbnail - in error", response);
        },
        () => {
          console.log("POST - getThumbnail - observable is now completed.");
        });
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
