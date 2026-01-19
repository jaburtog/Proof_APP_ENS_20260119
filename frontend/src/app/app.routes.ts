import { Routes } from '@angular/router';
import { UsuarioComponent } from './components/usuario/usuario.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { AplicacionComponent } from './components/aplicacion/aplicacion.component';

export const routes: Routes = [
  { path: '', redirectTo: '/usuarios', pathMatch: 'full' },
  { path: 'usuarios', component: UsuarioComponent },
  { path: 'perfiles', component: PerfilComponent },
  { path: 'aplicaciones', component: AplicacionComponent }
];
