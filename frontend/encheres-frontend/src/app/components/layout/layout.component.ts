import {Component, inject, signal} from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {CommonModule, DatePipe} from '@angular/common';
import {ClientContextService} from '../../services/client-context.service';


@Component({
  selector: 'app-layout',
  imports: [CommonModule, RouterOutlet, RouterLink, DatePipe],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css',
})
export class LayoutComponent {

  clientContext = inject(ClientContextService);
  today : Date = new Date();

  constructor() {
  }

ngOnInit() {
  console.info('dans ngOnInit() LayoutComponent ' );
}

}
