import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {MatSortModule} from '@angular/material/sort';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';

import { HttpClientModule } from '@angular/common/http';

import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerCreateComponent } from './customer-create/customer-create.component';
import { CustomerUpdateComponent } from './customer-update/customer-update.component';
import { CustomerDataFormComponent } from './customer-data-form/customer-data-form.component';
import { CustomerDeleteComponent } from './customer-delete/customer-delete.component';
import { ViewComponent } from './view/view.component';


@NgModule({
  declarations: [
    CustomerCreateComponent,
    CustomerUpdateComponent,
    CustomerDataFormComponent,
    CustomerDeleteComponent,
    ViewComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule,
    MatTableModule,
    HttpClientModule,
    NoopAnimationsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatSortModule,
    MatPaginatorModule,
    MatProgressSpinnerModule
  ],
  exports: [
    CustomerCreateComponent,
    CustomerUpdateComponent,
    CustomerRoutingModule,
    CustomerDeleteComponent,
    ViewComponent
  ]
})
export class CustomerModule { }
