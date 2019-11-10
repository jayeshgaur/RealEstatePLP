import { Injectable } from "@angular/core";
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { Observable } from "rxjs";


export class User {
  constructor(
    public status: string,
  ) { }
}

export class JwtResponse {
  constructor(
    public jwttoken: string,
  ) { }

}

@Injectable({
  providedIn: 'root'
})
export class EmsService {

  constructor(private myhttp: HttpClient) {

  }

  thumbnailFetchUrl: string = "https://south/generateThumbnail?width=100&height=100";

  getBlobThumbnail(a:any): Observable<Blob> {
    const headers = new HttpHeaders({
      'Content-Type': 'image/png',
      'Accept': 'image/png'
    });
    return this.myhttp.get<Blob>(a,
      { headers: headers, responseType: 'blob' as 'json' });
  }

  register(newUser: any) {
    return this.myhttp.post('http://localhost:9123/register', newUser, { responseType: 'json' });
  }

  getUser(useremail: any) {
    return this.myhttp.get("http://localhost:9123/finduser?userEmail=" + useremail);
  }

  authenticate(username: string, password: string) {
    console.log("Inside service authenticate.. email: " + username + " password: " + password);
    const reqbody = { userEmail: username, password: password };
    console.log(JSON.stringify(reqbody))
    return this.myhttp.post<any>('http://localhost:9123/authenticate', { userEmail: username, password: password });
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('userId');
    sessionStorage.removeItem('userRole');
    sessionStorage.removeItem('userName');
  }

  getEstates() {
    return this.myhttp.get('http://localhost:9123/getEstates');
  }

  download(estateId:any): Observable<Blob>{
    return this.myhttp.get("http://localhost:9123/pdfreport?estateId="+estateId, {'responseType':"blob"});
}

}