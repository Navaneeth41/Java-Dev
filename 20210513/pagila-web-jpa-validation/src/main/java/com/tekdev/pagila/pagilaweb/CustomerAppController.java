package com.tekdev.pagila.pagilaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.tekdev.pagila.pagilaweb.entity.Customer;
import com.tekdev.pagila.pagilaweb.service.CustomerDataService;

@Controller
@RequestMapping("/customer_app")
public class CustomerAppController {

	@Autowired
	CustomerDataService customerDataService;
	
	@GetMapping("/customer")
	public String getCustomer( Model model) {
		return "customerinfo";
	}
	
	@GetMapping("/customer/{customerId}")
	public String getCustomer(@PathVariable Long customerId, Model model) {
		model.addAttribute("customer", customerDataService.getCustomerById(customerId));
		return "customerinfo";
	}
	
	@PostMapping(path = "/customer")
	public String addCustomer(@ModelAttribute("customerFormData") Customer customer,  ModelMap model) {
		customerDataService.saveCustomer(customer);
        model.addAttribute("customer", customer);
		model.addAttribute("statusMessage", "Customer add is Successful.");
		return "customerinfo";
	}
	
	// put method is not suppored by html 1.x form method
	@PostMapping(path = "/update_customer/{customerId}")
	public RedirectView updateCustomer(@ModelAttribute("customerFormData") Customer customer,  ModelMap model) {
		customerDataService.updateCustomer(customer);
        model.addAttribute("customer", customer);
		model.addAttribute("statusMessage", "Customer Update is Successful.");
		return new RedirectView("/customer_app/customer/" + customer.getCustomerId());
	}

	// delete method is not suppored by html 1.x form method
	@GetMapping("/delete_customer/{customerId}")
	public RedirectView deleteCustomer(@PathVariable Long customerId, Model model) {
		boolean deleteStatus = customerDataService.deleteCustomer(customerId);
		model.addAttribute("statusMessage", deleteStatus ? "Customer delete is Successful." : "Customer delete is Failed.");
		return new RedirectView("/customer_app/customer");
	}
}
