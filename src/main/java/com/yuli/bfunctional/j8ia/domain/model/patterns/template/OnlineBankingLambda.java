//: com.yuli.bfunctional.j8ia.domain.model.patterns.template.OnlineBankingLambda.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.template;


import com.yuli.bfunctional.j8ia.domain.repositories.ICustomerRepository;

import java.util.function.Consumer;


public class OnlineBankingLambda {

    private final ICustomerRepository customerRepository;

    public OnlineBankingLambda(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void processCustomer(long id, Consumer<ICustomer> makeCustomerHappy) {

        ICustomer customer = this.customerRepository.getById(id);
        makeCustomerHappy.accept(this.customerRepository.getById(id));
    }

}///:~