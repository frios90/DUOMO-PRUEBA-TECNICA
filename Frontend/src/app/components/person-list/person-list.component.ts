import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonService } from '../../services/person.service';
import { Person } from '../../models/person.model';
import { PersonFormComponent } from '../person-form/person-form.component';

@Component({
  selector: 'app-person-list',
  standalone: true,
  imports: [CommonModule, PersonFormComponent],
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})

export class PersonListComponent implements OnInit {
  people: Person[] = [];
  loading = false;
  error = '';

  constructor(private personService: PersonService) {}

  ngOnInit(): void {
    this.loadPeople();
  }

  loadPeople(): void {
    this.loading = true;
    this.personService.getPeople().subscribe({
      next: (data) => {
        this.people = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error al cargar las personas';
        this.loading = false;
        console.error(err);
      }
    });
  }

  deletePerson(id: string): void {
    if (confirm('¿Estás seguro de eliminar esta persona?')) {
      this.personService.deletePerson(id).subscribe({
        next: () => {
          this.loadPeople();
        },
        error: (err) => {
          alert('Error al eliminar la persona');
          console.error(err);
        }
      });
    }
  }
}
