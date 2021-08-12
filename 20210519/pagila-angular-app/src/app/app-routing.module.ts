import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActorRoutingModule } from './actor/actor-routing.module';
import { CustomerRoutingModule } from './customer/customer-routing.module';
import { HomeComponent } from './home/home.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'customer',
    loadChildren: () => CustomerRoutingModule
  },
  {
    path: 'actor',
    loadChildren: () => ActorRoutingModule
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
