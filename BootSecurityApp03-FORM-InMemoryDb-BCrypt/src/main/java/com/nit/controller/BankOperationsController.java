package com.nit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankOperationsController {

	// @RequestMapping(method = RequestMethod.GET,value = "/")
	@GetMapping("/")
	public String showHome() {
		return "home";
	}// showHome

	@GetMapping("/offers")
	public String show_Offers() {
		return "show_offers";
	}// show_Offers

	@GetMapping("/balance")
	public String show_balance() {
		return "show_balance";
	}// show_balance

	@GetMapping("/aprovedloan")
	public String showAprovedLoan() {
		return "loan";
	}// showAprovedLoan
	
	@GetMapping("/denied")
	public String denied() {
		return "access_denied";
	}//denied
}// class
