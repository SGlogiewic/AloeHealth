package com.gcu;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.model.LoginModel;
import com.gcu.model.OrderModel;
import com.gcu.model.SearchOrdersModel;

@Controller
@RequestMapping("/aloehealth")
public class ControllerForPages {
	
	// uses dependency injection configuration found in the SpringConfig file 
	// to choose which busienssService will be utilized.
	@Autowired
	private OrdersBusinessServiceInterface ordersService;

	@GetMapping("/searchForm") 
	public String displaySearchForm(Model model)
	{
		// Display Login Form View
		model.addAttribute("title", "Search Orders");
		model.addAttribute("searchOrdersModel", new SearchOrdersModel());
		return "ordersSearchForm";
	}
	
	@GetMapping("/addNewForm") 
	public String displayAddNewForm(Model model)
	{
		// Display new order form
		model.addAttribute("title", "Add new order");
		model.addAttribute("orderModel", new OrderModel());
		return "ordersAddNewForm";
	}
	
	@PostMapping("/searchResults")
	public String showAllOrders(@Valid SearchOrdersModel searchModel, BindingResult bindingResult, Model model) { 
		System.out.println("Performing search results for " + searchModel.getSearchTerm());
		// Check for validation errors
        if (bindingResult.hasErrors()) 
        {
        	model.addAttribute("title", "Search for Orders");
            return "searchOrdersForm";
        }
		List<OrderModel> orders = ordersService.searchOrders(searchModel.getSearchTerm());  
	 	model.addAttribute("title", "Search for Orders");
		model.addAttribute("searchModel", searchModel);
		model.addAttribute("orders", orders);
		
		return "orders";
		
	}
	@GetMapping("/home") 
	public String showHomePage( Model model) { 
		 
		//List<OrderModel> orders = ordersService.getOrders();  
	 	//model.addAttribute("title", "Show all orders");
		//model.addAttribute("searchModel", new SearchOrdersModel());
		//model.addAttribute("orders", orders);
		
		return "home";
		
	}
	
	@GetMapping("/appointments") 
	public String showAppointmentsPage( Model model) { 
		 return "appointments";
		
	}
	
	@GetMapping("/services") 
	public String showMedicalServicesPage( Model model) { 
		 return "services";
		
	}
	
	@GetMapping("/financialcalculator") 
	public String showFinancialCalculatorPage( Model model) { 
		 return "calculator";
		
	}
	
	@GetMapping("/medicalstaff") 
	public String showMedicalStaffPage( Model model) { 
		 return "staff";
		
	}
	
	@GetMapping("/contacts") 
	public String showContactsPage( Model model) { 
		 return "contacts";
		
	}
	
	@GetMapping("/about") 
	public String showAboutPage( Model model) { 
		 return "about";
		
	}
	
	@GetMapping("/feedback") 
	public String showFeedbackPage( Model model) { 
		 return "feedback";
		
	}
	
	@GetMapping("/help") 
	public String showHelpPage( Model model) { 
		 return "help";
		
	}
	
	@GetMapping("/careers") 
	public String showCareersPage( Model model) { 
		 return "careers";
		
	}
	
	@GetMapping("/terms") 
	public String showTermsPage( Model model) { 
		 return "termsandprivacy";
		
	}
	
	@PostMapping("/addNew") 
	// process a request from the AddOrderForm.  Add a new order to the database.  Show all orders.
	public String addOrder(@Valid OrderModel newOrder, BindingResult bindingResult, Model model) {
		// add the new order
		ordersService.addOne(newOrder);
		
		// get updated list of all the orders
		List<OrderModel> orders = ordersService.getOrders(); 
		
		// display all orders
		model.addAttribute("orders", orders); 
		model.addAttribute("searchModel", new SearchOrdersModel()); 
		return "orders";
	}
	 
	
	
	@GetMapping("/admin") 
	public String showOrdersForAdmin( Model model) { 
		 
		// display all orders with delete and edit buttons
		List<OrderModel> orders = ordersService.getOrders();  
	 	model.addAttribute("title", "Edit or delete orders");
		model.addAttribute("searchModel", new SearchOrdersModel());
		model.addAttribute("orders", orders);
		
		// ordersAdmin page shows a table of orders including buttons for del and edit.
		return "ordersAdmin";
		
	}
	
	@PostMapping("/delete") 
	// process a request from the AddOrderForm.  Add a new order to the database.  Show all orders.
	public String deleteOrder(@Valid OrderModel order, BindingResult bindingResult, Model model) {
		// add the new order
		ordersService.deleteOne(order.getId());
		
		// get updated list of all the orders
		List<OrderModel> orders = ordersService.getOrders(); 
		
		// display all orders
		model.addAttribute("orders", orders); 
		model.addAttribute("searchModel", new SearchOrdersModel()); 
		return "ordersAdmin";
	}
	
	@PostMapping("/editForm") 
	public String displayEditForm(OrderModel orderModel, Model model)
	{
		// Display new order form
		model.addAttribute("title", "Edit order");
		model.addAttribute("orderModel", orderModel);
		return "ordersEditForm";
	}
	
	@PostMapping("/doUpdate") 
	// process a request from the AddOrderForm.  Add a new order to the database.  Show all orders.
	public String updateOrder(@Valid OrderModel order, BindingResult bindingResult, Model model) {
		// add the new order
		ordersService.updateOne(order.getId(), order);
		
		// get updated list of all the orders
		List<OrderModel> orders = ordersService.getOrders(); 
		
		// display all orders
		model.addAttribute("orders", orders); 
		model.addAttribute("searchModel", new SearchOrdersModel()); 
		return "ordersAdmin";
	}
}
