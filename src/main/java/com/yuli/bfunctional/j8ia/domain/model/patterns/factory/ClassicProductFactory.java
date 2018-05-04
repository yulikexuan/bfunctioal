//: com.yuli.bfunctional.j8ia.domain.model.patterns.factory.ClassicProductFactory.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.factory;


public class ClassicProductFactory implements IProductFactory {

	@Override
	public IFinanceProduct createProduct(String name) {
		switch(name){
			case "loan": return new Loan();
			case "stock": return new Stock();
			case "bond": return new Bond();
			default: throw new RuntimeException("No such product " + name);
		}
	}

}///:~