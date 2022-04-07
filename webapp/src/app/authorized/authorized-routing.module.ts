import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EditorComponent } from './editor/editor.component'; 
import { TasksComponent } from './tasks/tasks.component';
import { AuthorizedComponent } from './authorized.component';

const routes: Routes = [
  { path: '',
    component: AuthorizedComponent,
      children: [
        {path: 'tasks', component: TasksComponent},
        {path:'tasks/:taskId', component: EditorComponent},
        {path: '', redirectTo: 'tasks', pathMatch: 'full'} ]
   },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuthorizedRoutingModule { }
