import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Customer } from 'src/app/models';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-data-form',
  templateUrl: './customer-data-form.component.html',
  styleUrls: ['./customer-data-form.component.scss']
})
export class CustomerDataFormComponent implements OnInit {

  @Input() initialData: Customer | undefined;

  customerForm!: FormGroup;
  formError = false;

  constructor(private customerService: CustomerService, private router: Router) {
  }

  ngOnInit(): void {
    this.initForm();
  }

  ngAfterContentChecked() {
    if (this.formError) {
      this.formError = this.customerForm.invalid;
    }
  }

  private initForm() {
    this.customerForm = new FormGroup({
      'firstName': new FormControl(this.initialData ? this.initialData.firstName : null, Validators.required),
      'lastName': new FormControl(this.initialData ? this.initialData.lastName : null, Validators.required),
      'email': new FormControl(this.initialData ? this.initialData.email : null, [Validators.required, Validators.email]),
      'addressId': new FormControl(this.initialData ? this.initialData.addressId : null, [Validators.required]),
      'storeId': new FormControl(this.initialData ? this.initialData.storeId : null, [Validators.required])
    });
  }

  get formControls() {
    return this.customerForm.controls;
  }

  async onSubmit() {
    this.formError = false;
    if (!this.customerForm.valid) {
      console.log("Form is invalid...");
      this.formError = true;
      return;
    }
    var data = this.customerForm.value as Customer;
    if (this.initialData) {
      data.customerId = this.initialData?.customerId;
      // data.createDate = this.initialData?.createDate;
    } else {
      // data.createDate = new Date();
    }
    await this.customerService.saveCustomerData(data);
    this.router.navigate(['customer']);
  }


  onCancel() {
    this.router.navigate(['customer']);
  }
}
