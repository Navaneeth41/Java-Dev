import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerCreateComponent } from './customer-create/customer-create.component';
import { CustomerUpdateComponent } from './customer-update/customer-update.component';
import { CustomerDataFormComponent } from './customer-data-form/customer-data-form.component';


@NgModule({
  declarations: [
    CustomerCreateComponent,
    CustomerUpdateComponent,
    CustomerDataFormComponent
  ],
  imports: [
    CommonModule,
    CustomerRoutingModule
  ],
  exports: [
    CustomerCreateComponent,
    CustomerUpdateComponent,
    CustomerRoutingModule
  ]
})
export class CustomerModule { }
