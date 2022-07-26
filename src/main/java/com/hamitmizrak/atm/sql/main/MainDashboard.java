package com.hamitmizrak.atm.sql.main;

import java.util.List;

import com.hamitmizrak.atm.sql.dao.BankDao;
import com.hamitmizrak.atm.sql.dto.BankDto;

public class MainDashboard {
	// encapsulation
	
	public static void main(String[] args) {
		// BankController bankController = new BankController();
		// BankDto bankDto = new BankDto();
		// bankDto.setBankName("Akbank");
		// bankDto.setBranchName("Artvin");
		// bankController.create(bankDto);
		// AvmDto avmDto = new AvmDto();
		// avmDto.avmMain();
		// BankDto bankDto = new BankDto();
		// bankDto.setId(3L);
		
		// CustomerController cusController = new CustomerController();
		// CustomerDto customerDto = new CustomerDto("tolga", "faruk", "1234345",
		// bankDto);
		// cusController.create(customerDto);
		BankDao customerDao = new BankDao();
		
		System.out.println("çalıştı");
		List<BankDto> listem = customerDao.innerJo();
		listem.forEach(System.out::println);
		
	}
	
}
