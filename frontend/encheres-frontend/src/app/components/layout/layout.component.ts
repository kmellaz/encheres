import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {DatePipe} from '@angular/common';
import {SendClientIdService} from '../../services/send-client-id.service';


@Component({
  selector: 'app-layout',
  imports: [RouterOutlet, RouterLink, DatePipe],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css',
})
export class LayoutComponent {
  today : Date = new Date();
  clientId: string | null = null ;

  constructor(private sendClientIdService: SendClientIdService,) {
  }

ngOnInit() {
    this.getClientId();
}

  getClientId(): void {
    this.sendClientIdService.client$.subscribe(c => {
      this.clientId = c;
    });
  }
}
