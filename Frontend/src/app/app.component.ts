import { Component } from '@angular/core';
import { PersonListComponent } from './components/person-list/person-list.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    PersonListComponent
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frontend';
}
