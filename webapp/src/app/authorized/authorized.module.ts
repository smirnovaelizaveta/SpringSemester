import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MatCardModule } from '@angular/material/card';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatListModule } from '@angular/material/list'; 
import { MatButtonModule } from '@angular/material/button'; 
import { MatSidenavModule } from '@angular/material/sidenav'; 
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTreeModule } from '@angular/material/tree'; 
import { MatFormFieldModule } from '@angular/material/form-field'; 
import { MatInputModule } from '@angular/material/input'; 
import { CodemirrorModule } from '@ctrl/ngx-codemirror';
import { EditorComponent } from './editor/editor.component'; 
import { TasksComponent } from './tasks/tasks.component';
import { Routes, RouterModule } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { AuthorizedRoutingModule } from './authorized-routing.module';
import { AuthorizedComponent } from './authorized.component';
import { TasksService } from './service/tasks.service';
 import { MatTabsModule } from '@angular/material/tabs'; 


@NgModule({
  providers: [
    TasksService
  ],
  declarations: [
    AuthorizedComponent,
    MenuComponent,
    TasksComponent,
    EditorComponent
  ],
  imports: [
    CommonModule,
    AuthorizedRoutingModule,
    HttpClientModule,
    MatCardModule,
    FlexLayoutModule,
    MatListModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatToolbarModule,
    MatTreeModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    CodemirrorModule,
    MatTabsModule
  ]
})
export class AuthorizedModule { }
