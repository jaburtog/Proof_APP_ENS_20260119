import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Aplicacion } from '../../models/models';
import { AplicacionService } from '../../services/aplicacion.service';

@Component({
  selector: 'app-aplicacion',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './aplicacion.component.html',
  styleUrl: './aplicacion.component.css'
})
export class AplicacionComponent implements OnInit {
  aplicaciones: Aplicacion[] = [];
  selectedAplicacion: Aplicacion = this.getEmptyAplicacion();
  isEditing = false;
  showForm = false;

  constructor(private aplicacionService: AplicacionService) {}

  ngOnInit(): void {
    this.loadAplicaciones();
  }

  loadAplicaciones(): void {
    this.aplicacionService.getAll().subscribe({
      next: (data) => this.aplicaciones = data,
      error: (err) => console.error('Error loading aplicaciones', err)
    });
  }

  getEmptyAplicacion(): Aplicacion {
    return { nombre: '', descripcion: '', url: '', activo: true };
  }

  newAplicacion(): void {
    this.selectedAplicacion = this.getEmptyAplicacion();
    this.isEditing = false;
    this.showForm = true;
  }

  editAplicacion(aplicacion: Aplicacion): void {
    this.selectedAplicacion = { ...aplicacion };
    this.isEditing = true;
    this.showForm = true;
  }

  saveAplicacion(): void {
    if (this.isEditing && this.selectedAplicacion.id) {
      this.aplicacionService.update(this.selectedAplicacion.id, this.selectedAplicacion).subscribe({
        next: () => { this.loadAplicaciones(); this.cancelForm(); },
        error: (err) => console.error('Error updating aplicacion', err)
      });
    } else {
      this.aplicacionService.create(this.selectedAplicacion).subscribe({
        next: () => { this.loadAplicaciones(); this.cancelForm(); },
        error: (err) => console.error('Error creating aplicacion', err)
      });
    }
  }

  deleteAplicacion(id: number | undefined): void {
    if (id && confirm('¿Está seguro de eliminar esta aplicación?')) {
      this.aplicacionService.delete(id).subscribe({
        next: () => this.loadAplicaciones(),
        error: (err) => console.error('Error deleting aplicacion', err)
      });
    }
  }

  cancelForm(): void {
    this.showForm = false;
    this.selectedAplicacion = this.getEmptyAplicacion();
    this.isEditing = false;
  }
}
