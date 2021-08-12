import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Actor } from 'src/app/models/actor.model';
import { ActorService } from 'src/app/services/actor.service';

@Component({
  selector: 'app-actor-data-form',
  templateUrl: './actor-data-form.component.html',
  styleUrls: ['./actor-data-form.component.scss']
})
export class ActorDataFormComponent implements OnInit {

  @Input() initialData: Actor | undefined;

  actorForm!: FormGroup;
  formError = false;

  constructor(private actorService: ActorService, private router: Router) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  ngAfterContentChecked() {
    if (this.formError) {
      this.formError = this.actorForm.invalid;
    }
  }

  private initForm() {
    this.actorForm = new FormGroup({
      'firstName': new FormControl(this.initialData ? this.initialData.firstName : null, Validators.required),
      'lastName': new FormControl(this.initialData ? this.initialData.lastName : null, Validators.required)
    });
  }

  get formControls() {
    return this.actorForm.controls;
  }

  async onSubmit() {
    this.formError = false;
    if (!this.actorForm.valid) {
      console.log("Form is invalid...");
      this.formError = true;
      return;
    }
    var data = this.actorForm.value as Actor;
    if (this.initialData) {
      data.actorId = this.initialData?.actorId;
      // data.createDate = this.initialData?.createDate;
    } else {
      // data.createDate = new Date();
    }
    await this.actorService.saveActorData(data);
    this.router.navigate(['actor']);
  }


  onCancel() {
    this.router.navigate(['actor']);
  }
}
