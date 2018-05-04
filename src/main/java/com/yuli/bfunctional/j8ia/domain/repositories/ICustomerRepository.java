//: com.yuli.bfunctional.j8ia.domain.repositories.ICustomerRepository.java


package com.yuli.bfunctional.j8ia.domain.repositories;


import com.yuli.bfunctional.j8ia.domain.model.patterns.template.ICustomer;


public interface ICustomerRepository {

	ICustomer getById(long id);

}///:~