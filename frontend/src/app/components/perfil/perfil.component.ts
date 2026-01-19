import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Perfil } from '../../models/models';
import { PerfilService } from '../../services/perfil.service';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent implements OnInit {
  perfiles: Perfil[] = [];
  selectedPerfil: Perfil = this.getEmptyPerfil();
  isEditing = false;
  showForm = false;

  constructor(private perfilService: PerfilService) {}

  ngOnInit(): void {
    this.loadPerfiles();
  }

  loadPerfiles(): void {
    this.perfilService.getAll().subscribe({
      next: (data) => this.perfiles = data,
      error: (err) => console.error('Error loading perfiles', err)
    });
  }

  getEmptyPerfil(): Perfil {
    return { nombre: '', descripcion: '', activo: true };
  }

  newPerfil(): void {
    this.selectedPerfil = this.getEmptyPerfil();
    this.isEditing = false;
    this.showForm = true;
  }

  editPerfil(perfil: Perfil): void {
    this.selectedPerfil = { ...perfil };
    this.isEditing = true;
    this.showForm = true;
  }

  savePerfil(): void {
    if (this.isEditing && this.selectedPerfil.id) {
      this.perfilService.update(this.selectedPerfil.id, this.selectedPerfil).subscribe({
        next: () => { this.loadPerfiles(); this.cancelForm(); },
        error: (err) => console.error('Error updating perfil', err)
      });
    } else {
      this.perfilService.create(this.selectedPerfil).subscribe({
        next: () => { this.loadPerfiles(); this.cancelForm(); },
        error: (err) => console.error('Error creating perfil', err)
      });
    }
  }

  deletePerfil(id: number | undefined): void {
    if (id && confirm('¿Está seguro de eliminar este perfil?')) {
      this.perfilService.delete(id).subscribe({
        next: () => this.loadPerfiles(),
        error: (err) => console.error('Error deleting perfil', err)
      });
    }
  }

  cancelForm(): void {
    this.showForm = false;
    this.selectedPerfil = this.getEmptyPerfil();
    this.isEditing = false;
  }
}
