//: com.yuli.bfunctional.j8ia.domain.model.patterns.template.OnlineBanking.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.template;


import com.yuli.bfunctional.j8ia.domain.repositories.ICustomerRepository;


public abstract class OnlineBanking {

	private final ICustomerRepository customerRepository;

	public OnlineBanking(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public void processCustomer(long id) {
		ICustomer customer = this.customerRepository.getById(id);
		this.makeCustomerHappy(customer);
	}

	abstract void makeCustomerHappy(ICustomer c);

}///:~