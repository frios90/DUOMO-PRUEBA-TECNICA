import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PersonService } from '../../services/person.service';
import { Person } from '../../models/person.model';
import { Region } from '../../models/region.model';
import { Commune } from '../../models/commune.model';
import Swal from 'sweetalert2';

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
    age: 0,
    regionId: '',
    communeId: ''
  };

  regions: Region[] = [];
  communes: Commune[] = [];
  submitting = false;
  error = '';

  validations = {
    name: { valid: false, touched: false, message: 'Nombre es obligatorio (mínimo 2 caracteres)' },
    lastName: { valid: false, touched: false, message: 'Apellido es obligatorio (mínimo 2 caracteres)' },
    email: { valid: false, touched: false, message: 'Email debe ser válido (ej: usuario@email.com)' },
    age: { valid: false, touched: false, message: 'Edad debe ser mayor o igual a 18 años' },
    region: { valid: false, touched: false, message: 'Debes seleccionar una región' },
    commune: { valid: false, touched: false, message: 'Debes seleccionar una comuna' }
  };

  progress = 0;

  constructor(private personService: PersonService) {}

  ngOnInit(): void {
    this.loadRegions();
  }

  loadRegions(): void {
    this.personService.getRegions().subscribe({
      next: (data) => {
        this.regions = data;
      },
      error: (err) => {
        console.error('Error cargando regiones:', err);
      }
    });
  }

  onRegionChange(): void {
    this.person.communeId = '';
    this.communes = [];
    this.validations.commune.valid = false;
    this.validations.commune.touched = true;

    if (this.person.regionId) {
      this.validations.region.valid = true;
      this.validations.region.touched = true;
      this.personService.getCommunes(this.person.regionId).subscribe({
        next: (data) => {
          this.communes = data;
          this.updateProgress();
        },
        error: (err) => {
          console.error('Error cargando comunas:', err);
        }
      });
    } else {
      this.validations.region.valid = false;
      this.validations.region.touched = true;
      this.updateProgress();
    }
  }

  validateName(): void {
    const value = this.person.name?.trim() || '';
    this.validations.name.valid = value.length >= 2;
    this.validations.name.touched = true;
    this.updateProgress();
  }

  validateLastName(): void {
    const value = this.person.lastName?.trim() || '';
    this.validations.lastName.valid = value.length >= 2;
    this.validations.lastName.touched = true;
    this.updateProgress();
  }

  validateEmail(): void {
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    const value = this.person.email?.trim() || '';
    this.validations.email.valid = emailRegex.test(value);
    this.validations.email.touched = true;
    this.updateProgress();
  }

  validateAge(): void {
    const value = this.person.age;
    this.validations.age.valid = value !== null && value !== undefined && value >= 18 && value <= 150;
    this.validations.age.touched = true;
    this.updateProgress();
  }

  validateCommune(): void {
    this.validations.commune.valid = !!this.person.communeId;
    this.validations.commune.touched = true;
    this.updateProgress();
  }

  updateProgress(): void {
    const totalFields = 6;
    let validCount = 0;
    if (this.validations.name.valid) validCount++;
    if (this.validations.lastName.valid) validCount++;
    if (this.validations.email.valid) validCount++;
    if (this.validations.age.valid) validCount++;
    if (this.validations.region.valid) validCount++;
    if (this.validations.commune.valid) validCount++;
    this.progress = Math.round((validCount / totalFields) * 100);
  }

  isFormValid(): boolean {return this.progress === 100;}

  onSubmit(): void {
    if (!this.isFormValid()) return;
    this.submitting = true;
    this.error = '';
    this.personService.createPerson(this.person).subscribe({
      next: () => {
        this.submitting = false;
        this.resetForm();
        this.personCreated.emit();
        Swal.fire({
          title: '¡Creado!',
          text: `Registro creado correctamente`,
          icon: 'success',
          timer: 1500,
          timerProgressBar: true,
          showConfirmButton: false,
          allowOutsideClick: false,
          allowEscapeKey: false
        }).then(() => {
          const modalElement = document.getElementById('personModal');
          if (modalElement) {
            const modal = (window as any).bootstrap.Modal.getInstance(modalElement);
            if (modal) modal.hide();
          }
        });
      },
      error: (err) => {
        this.submitting = false;
        this.error = err.error?.message || 'Error al crear la persona';
        Swal.fire({
          title: 'Error',
          text: this.error,
          icon: 'error',
          confirmButtonColor: '#d33',
          confirmButtonText: 'Cerrar'
        });
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
    this.progress = 0;
    Object.keys(this.validations).forEach(key => {
      this.validations[key as keyof typeof this.validations] = {
        valid: false,
        touched: false,
        message: this.validations[key as keyof typeof this.validations].message
      };
    });
  }
}