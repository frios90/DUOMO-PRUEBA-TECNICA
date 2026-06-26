import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonService } from '../../services/person.service';
import { Person } from '../../models/person.model';
import { PersonFormComponent } from '../person-form/person-form.component';
import Swal from 'sweetalert2';

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
    // uso esto apra simular una demora en la carga y ques e vea el loader y su manejo
    // setTimeout(() => {
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
    // }, 1000);
  }

  deletePerson(id: string): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'Esta acción no se puede deshacer',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#6c757d',
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.personService.deletePerson(id).subscribe({
          next: () => {
            Swal.fire(
              '¡Eliminado!',
              'La persona fue eliminada correctamente',
              'success'
            );
            this.loadPeople();
          },
          error: (err) => {
            Swal.fire(
              'Error',
              'No se pudo eliminar la persona',
              'error'
            );
            console.error(err);
          }
        });
      }
    });
  }
}
