import {Component} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {EncheresService} from '../../services/encheres.service';
import {Observable} from 'rxjs';
import {Enchere} from '../../models/enchere';
import {CommonModule, Location} from '@angular/common';

@Component({
  selector: 'app-detail-enchere',
  imports: [CommonModule],
  templateUrl: './detail-enchere.component.html',
  styleUrl: './detail-enchere.component.css',
})
export class DetailEnchereComponent {
  idEnchere: string = '-1';
  enchere$: Observable<Enchere> | null = null;

  constructor(private http: HttpClient,
              private router: Router,
              private enchereService: EncheresService,
              private route: ActivatedRoute,
              private location: Location) {

  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.idEnchere = params.get('idEnchere') ?? '-1';
      this.loadEnchere();
    })

  }

  loadEnchere() {
    this.enchere$ = this.enchereService.getById('', this.idEnchere);
  }

  goBack(): void {
    this.location.back();
  }
}
