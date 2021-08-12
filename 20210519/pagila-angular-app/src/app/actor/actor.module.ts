import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {MatSortModule} from '@angular/material/sort';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';

import { ActorRoutingModule } from './actor-routing.module';
import { ActorComponent } from './actor/actor.component';
import { ActorCreateComponent } from './actor-create/actor-create.component';
import { ActorUpdateComponent } from './actor-update/actor-update.component';
import { ActorDeleteComponent } from './actor-delete/actor-delete.component';
import { ViewComponent } from './view/view.component';
import { ActorDataFormComponent } from './actor-data-form/actor-data-form.component';


@NgModule({
  declarations: [
    ActorComponent,
    ActorCreateComponent,
    ActorUpdateComponent,
    ActorDeleteComponent,
    ViewComponent,
    ActorDataFormComponent
  ],
  imports: [
    CommonModule,
    ActorRoutingModule,
    MatTableModule,
    NoopAnimationsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatSortModule,
    MatPaginatorModule,
    MatProgressSpinnerModule
  ],
  exports: [
    ActorComponent,
    ActorCreateComponent,
    ActorUpdateComponent,
    ActorDeleteComponent,
    ViewComponent
  ]
})
export class ActorModule { }
