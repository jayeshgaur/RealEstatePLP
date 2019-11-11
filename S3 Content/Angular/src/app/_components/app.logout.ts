import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmsService } from '../_service/app.emsservice';


@Component({
  selector: 'logout',
  template:``
})
export class LogoutComponent implements OnInit {

  constructor(
    private service:EmsService,
    private router: Router) {

  }

  ngOnInit() {
    this.service.logOut();
    this.router.navigate(['login']);
  }

}