import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Customer } from 'src/app/models';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-customer-update',
  templateUrl: './customer-update.component.html',
  styleUrls: ['./customer-update.component.scss']
})
export class CustomerUpdateComponent implements OnInit {
  customerId: string | null = null;
  customer: Customer | undefined;
  constructor(private route: ActivatedRoute, private accountDataService: CustomerService) { }

  async ngOnInit() {
    this.customerId = this.route.snapshot.params.customerId;
    if (this.customerId != null) {
      this.customer = await this.accountDataService.fetchCustomerData(this.customerId);
    }
  }
}
