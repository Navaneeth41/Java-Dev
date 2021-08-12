import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActorCreateComponent } from './actor-create/actor-create.component';
import { ActorUpdateComponent } from './actor-update/actor-update.component';
import { ViewComponent } from './view/view.component';

const routes: Routes = [{
  path: '',
  component: ViewComponent
},
{
  path: 'new',
  component: ActorCreateComponent
},
{
  path: 'edit/:actorId',
  component: ActorUpdateComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ActorRoutingModule { }
