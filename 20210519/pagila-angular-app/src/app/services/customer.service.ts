import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from '../models';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private SRVC_URL = "http://localhost:8081/customer_data"; //from env

  constructor(private httpClient: HttpClient) { }

  async fetchAllCustomerData(): Promise<Customer[]> {
    return this.httpClient.get(this.SRVC_URL + '/customer').toPromise() as Promise<Customer[]>;
  }

  async fetchCustomerData(CustomerId: any): Promise<Customer> {
    return this.httpClient.get(this.SRVC_URL + '/customer/' + CustomerId).toPromise() as Promise<Customer>;
  }

  async removeCustomer(CustomerId: number) {
    return this.httpClient.delete(this.SRVC_URL + '/customer/' + CustomerId).toPromise();
  }

  async saveCustomerData(data: Customer) {
    return (data.customerId == null) ?
      this.httpClient.post(this.SRVC_URL + '/customer', data).toPromise() as Promise<Customer> :
      this.httpClient.put(this.SRVC_URL + '/customer', data).toPromise() as Promise<Customer>;
  }
}
