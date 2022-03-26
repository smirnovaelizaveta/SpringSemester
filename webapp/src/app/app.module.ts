import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatListModule } from '@angular/material/list'; 
import { MatButtonModule } from '@angular/material/button'; 
import { MatSidenavModule } from '@angular/material/sidenav'; 
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTreeModule } from '@angular/material/tree'; 
import { CodemirrorModule } from '@ctrl/ngx-codemirror';
import { EditorComponent } from './editor/editor.component'; 
import { TasksComponent } from './tasks/tasks.component';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path: 'tasks/:taskId', component: EditorComponent
  },
  {
    path: 'tasks', component: TasksComponent
  },
  {
    path: '', redirectTo: '/tasks', pathMatch: 'full' 
  }
];

@NgModule({
  declarations: [
    AppComponent,
    TasksComponent,
    EditorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    FlexLayoutModule,
    MatListModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatToolbarModule,
    MatTreeModule,
    FormsModule,
    CodemirrorModule,
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
