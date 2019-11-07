import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class EmsService{

    constructor(private myhttp: HttpClient){

    }

    thumbnailFetchUrl : string = "https://south/generateThumbnail?width=100&height=100";
    getBlobThumbnail(): Observable<Blob> {
        const headers = new HttpHeaders({
          'Content-Type': 'image/png',
          'Accept': 'image/png'      
        });
        return this.myhttp.get<Blob>("http://localhost:9123/downloadFile/6",
          {headers:headers, responseType: 'blob' as 'json' });
      }
}