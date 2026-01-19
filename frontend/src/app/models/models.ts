export interface Usuario {
  id?: number;
  username: string;
  nombre: string;
  apellido: string;
  email: string;
  password?: string;
  perfilId?: number;
  perfilNombre?: string;
  activo?: boolean;
  fechaCreacion?: Date;
  fechaModificacion?: Date;
}

export interface Perfil {
  id?: number;
  nombre: string;
  descripcion?: string;
  activo?: boolean;
  fechaCreacion?: Date;
  fechaModificacion?: Date;
}

export interface Aplicacion {
  id?: number;
  nombre: string;
  descripcion?: string;
  url?: string;
  activo?: boolean;
  fechaCreacion?: Date;
  fechaModificacion?: Date;
}

export interface Seccion {
  id?: number;
  nombre: string;
  descripcion?: string;
  ruta?: string;
  aplicacion?: Aplicacion;
  activo?: boolean;
  fechaCreacion?: Date;
  fechaModificacion?: Date;
}

export interface Autoriz {
  id?: number;
  perfil?: Perfil;
  seccion?: Seccion;
  puedeLeer?: boolean;
  puedeEscribir?: boolean;
  puedeEliminar?: boolean;
  activo?: boolean;
  fechaCreacion?: Date;
  fechaModificacion?: Date;
}
