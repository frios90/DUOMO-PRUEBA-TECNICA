import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PersonService } from '../../services/person.service';
import { Person } from '../../models/person.model';
import { PersonFormComponent } from '../person-form/person-form.component';
import { Paginate } from '../../models/paginate.model';
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

  currentPage: number = 0;
  pageSize: number = 10;
  totalElements: number = 0;
  totalPages: number = 0;
  first: boolean = true;
  last: boolean = true;

  Math = Math;

  constructor(private personService: PersonService) {}

  ngOnInit(): void {
    this.loadPeople();
  }

  loadPeople(): void {
    this.loading = true;

    this.personService.getPaginatePeople(this.currentPage, this.pageSize)
      .subscribe({
        next: (data: Paginate) => {
          this.people = data.content;
          this.totalElements = data.totalElements;
          this.totalPages = data.totalPages;
          this.first = data.first;
          this.last = data.last;
          this.loading = false;
        },
        error: (err) => {
          this.error = 'Error al cargar las personas';
          this.loading = false;
          console.error(err);
        }
      });
  }

  previousPage(): void {
    if (!this.first) {
      this.currentPage--;
      this.loadPeople();
    }
  }

  nextPage(): void {
    if (!this.last) {
      this.currentPage++;
      this.loadPeople();
    }
  }

  changePageSize(event: any): void {
    this.pageSize = Number(event.target.value);
    this.currentPage = 0;
    this.loadPeople();
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
            Swal.fire('¡Eliminado!', 'La persona fue eliminada correctamente', 'success');
            this.loadPeople();
          },
          error: (err) => {
            Swal.fire('Error', 'No se pudo eliminar la persona', 'error');
            console.error(err);
          }
        });
      }
    });
  }
}