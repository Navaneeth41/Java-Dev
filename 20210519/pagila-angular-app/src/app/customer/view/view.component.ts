import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Customer } from 'src/app/models';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})
export class ViewComponent implements OnInit {
  customers: Customer[] = [];
  displayedColumns= ["customerId", "firstName", "lastName", "email", "createDate", "lastUpdate", "actions"];
  isLoading = true;
  errorMsg: string|undefined;
  dataSource: MatTableDataSource<Customer>|undefined;
  @ViewChild(MatPaginator) paginator: MatPaginator|undefined;


  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.initialize();
  }

  ngAfterViewInit() {
    this.setupPaginator();
  }

  private async initialize() {
    await this.fetchCustomerData();
    this.isLoading = false;
    this.dataSource = new MatTableDataSource(this.customers);
    this.setupPaginator();
  }

  private async fetchCustomerData () {
    this.customers = await this.customerService.fetchAllCustomerData();

  }

  setupPaginator() {
    if (this.paginator && this.dataSource) {
      this.dataSource.paginator = this.paginator;
    }
  }

  async customerRemove(accountId: number) {
    this.isLoading = true;
    this.errorMsg = "";
    try {
      await this.customerService.removeCustomer(accountId);
      this.initialize();
    } catch(error) {
      console.log(error);
      this.errorMsg = "Delete is failed. " + error?.error?.message ;
      this.isLoading = false;
    }
  }

}
