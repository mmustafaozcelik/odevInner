package com.hamitmizrak.atm.sql.dto;

import lombok.Data;

@Data
// Customer(N) Bank(1)
public class CustomerDto extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	// Object variable
	private String customerName;
	private String customerSurName;
	private String customerIdentity;
	
	// composition
	private BankDto bankDto;
	
	// parametresiz constructor
	public CustomerDto() {
	}
	
	// parametreli constructor
	public CustomerDto(String customerName, String customerSurName, String customerIdentity, BankDto bankDto) {
		super();
		this.customerName = customerName;
		this.customerSurName = customerSurName;
		this.customerIdentity = customerIdentity;
		this.bankDto = bankDto;
	}
	
	// parametreli constructor
	public CustomerDto(Long id, String customerName, String customerSurName, String customerIdentity) {
		super(id);
		this.customerName = customerName;
		this.customerSurName = customerSurName;
		this.customerIdentity = customerIdentity;
	}
	
}
