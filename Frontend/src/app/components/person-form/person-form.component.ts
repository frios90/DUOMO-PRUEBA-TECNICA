import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PersonService } from '../../services/person.service';
import { Person, Region, Commune } from '../../models/person.model';

@Component({
  selector: 'app-person-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './person-form.component.html',
  styleUrls: ['./person-form.component.css']
})
export class PersonFormComponent implements OnInit {
  @Output() personCreated = new EventEmitter<void>();

  person: Person = {
    name: '',
    lastName: '',
    email: '',
    age: 18,
    regionId: '',
    communeId: ''
  };

  regions: Region[] = [];
  communes: Commune[] = [];
  submitting = false;
  error = '';

  constructor(private personService: PersonService) {}

  ngOnInit(): void {
    this.loadRegions();
  }

  loadRegions(): void {
    this.personService.getRegions().subscribe({
      next: (data) => {this.regions = data;},
      error: (err) => {console.error('Error cargando regiones:', err);}
    });
  }

  onRegionChange(): void {
    this.person.communeId = '';
    this.communes = [];
    if (this.person.regionId) {
      this.personService.getCommunes(this.person.regionId).subscribe({
        next: (data) => {this.communes = data;},
        error: (err) => {console.error('Error cargando comunas:', err);}
      });
    }
  }

  onSubmit(): void {
    this.submitting = true;
    this.error = '';
    this.personService.createPerson(this.person).subscribe({
      next: () => {
        this.submitting = false;
        this.resetForm();
        this.personCreated.emit();
        const modalElement = document.getElementById('personModal');
        if (modalElement) {
          const modal = (window as any).bootstrap.Modal.getInstance(modalElement);
          if (modal) modal.hide();
        }
      },
      error: (err) => {
        this.submitting = false;
        this.error = err.error?.message || 'Error al crear la persona';
        console.error(err);
      }
    });
  }

  resetForm(): void {
    this.person = {
      name: '',
      lastName: '',
      email: '',
      age: 18,
      regionId: '',
      communeId: ''
    };
    this.communes = [];
    this.error = '';
  }
}
