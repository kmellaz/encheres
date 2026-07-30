import {Component, inject, OnInit} from '@angular/core';
import {Router, RouterLink, RouterOutlet} from '@angular/router';
import {CommonModule, DatePipe} from '@angular/common';
import {ClientContextService} from '../../services/client-context.service';
import {AuthService} from '../../services/auth.service';


@Component({
  selector: 'app-layout',
  imports: [CommonModule, RouterOutlet, RouterLink, DatePipe],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.css',
})
export class LayoutComponent implements OnInit{

  readonly clientContext = inject(ClientContextService);
  readonly today : Date = new Date();

  constructor(private authService : AuthService, private router: Router) {
  }

  ngOnInit() {
    console.info('dans ngOnInit() LayoutComponent ' );
  }

  logout(): void {
    this.authService.logout();
    this.clientContext.clear();
    this.router.navigate(['/login']);
  }

}
