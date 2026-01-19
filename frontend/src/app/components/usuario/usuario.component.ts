import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Usuario, Perfil } from '../../models/models';
import { UsuarioService } from '../../services/usuario.service';
import { PerfilService } from '../../services/perfil.service';

@Component({
  selector: 'app-usuario',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './usuario.component.html',
  styleUrl: './usuario.component.css'
})
export class UsuarioComponent implements OnInit {
  usuarios: Usuario[] = [];
  perfiles: Perfil[] = [];
  selectedUsuario: Usuario = this.getEmptyUsuario();
  isEditing = false;
  showForm = false;

  constructor(
    private usuarioService: UsuarioService,
    private perfilService: PerfilService
  ) {}

  ngOnInit(): void {
    this.loadUsuarios();
    this.loadPerfiles();
  }

  loadUsuarios(): void {
    this.usuarioService.getAll().subscribe({
      next: (data) => this.usuarios = data,
      error: (err) => console.error('Error loading usuarios', err)
    });
  }

  loadPerfiles(): void {
    this.perfilService.getAll().subscribe({
      next: (data) => this.perfiles = data,
      error: (err) => console.error('Error loading perfiles', err)
    });
  }

  getEmptyUsuario(): Usuario {
    return {
      username: '',
      nombre: '',
      apellido: '',
      email: '',
      password: '',
      activo: true
    };
  }

  newUsuario(): void {
    this.selectedUsuario = this.getEmptyUsuario();
    this.isEditing = false;
    this.showForm = true;
  }

  editUsuario(usuario: Usuario): void {
    this.selectedUsuario = { ...usuario };
    this.isEditing = true;
    this.showForm = true;
  }

  saveUsuario(): void {
    if (this.isEditing && this.selectedUsuario.id) {
      this.usuarioService.update(this.selectedUsuario.id, this.selectedUsuario).subscribe({
        next: () => {
          this.loadUsuarios();
          this.cancelForm();
        },
        error: (err) => console.error('Error updating usuario', err)
      });
    } else {
      this.usuarioService.create(this.selectedUsuario).subscribe({
        next: () => {
          this.loadUsuarios();
          this.cancelForm();
        },
        error: (err) => console.error('Error creating usuario', err)
      });
    }
  }

  deleteUsuario(id: number | undefined): void {
    if (id && confirm('¿Está seguro de eliminar este usuario?')) {
      this.usuarioService.delete(id).subscribe({
        next: () => this.loadUsuarios(),
        error: (err) => console.error('Error deleting usuario', err)
      });
    }
  }

  cancelForm(): void {
    this.showForm = false;
    this.selectedUsuario = this.getEmptyUsuario();
    this.isEditing = false;
  }
}
