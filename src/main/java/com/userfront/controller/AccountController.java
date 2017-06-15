package com.userfront.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.userfront.domain.PrimaryAccount;
import com.userfront.domain.PrimaryTransaction;
import com.userfront.domain.SavingsAccount;
import com.userfront.domain.SavingsTransaction;
import com.userfront.domain.User;
import com.userfront.domain.service.AccountService;
import com.userfront.domain.service.TransactionService;
import com.userfront.domain.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private AccountService accountService;
	
	@Autowired
	private TransactionService transactionService;
	
	@RequestMapping("/primaryAccount")
	public String primaryAccount(Model model, Principal prinicipal){
	
		List<PrimaryTransaction> primaryTransactionList = transactionService.findPrimaryTransactionList(prinicipal.getName());
		
		User user = userService.findByUsername(prinicipal.getName());
		PrimaryAccount primaryAccount = user.getPrimaryAccount();
		
		model.addAttribute("primaryAccount", primaryAccount);
		model.addAttribute("primaryTransactionList", primaryTransactionList);
		
		return "primaryAccount";
	}
	
	@RequestMapping("/savingsAccount")
	public String savingsAccount(Model model, Principal principal){
		
		List<SavingsTransaction> savingsTransactionList = transactionService.findSaveTransactionList(principal.getName());
				
		User user = userService.findByUsername(principal.getName());
		SavingsAccount savingsAccount = user.getSavingsAccount();
	
		model.addAttribute("savingsAccount", savingsAccount);
		model.addAttribute("savingsTransactionList", savingsTransactionList);
		
		return "savingsAccount";
	}
	
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
	public String deposit(Model model){
		model.addAttribute("accountType", "");
		model.addAttribute("amount", "");
		
		return "deposit";
	}
	
	@RequestMapping(value = "/deposit",  method = RequestMethod.POST)
	public String depostPOST(@ModelAttribute("amount")String amount, @ModelAttribute("accountType") String accountType, Principal principal){
		accountService.deposit(accountType, Double.parseDouble(amount), principal);
		
		return "redirect:/userfront";
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET)
	public String withdraw(Model model){
		model.addAttribute("accountType", "");
		model.addAttribute("amount", "");
		
		return "withdraw";
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public String withdrawPOST(@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType, Principal principal){
		accountService.withdraw(accountType, Double.parseDouble(amount), principal);
	
		return "redirect:/userfront";
	}
}
