//: com.yuli.bfunctional.j8ia.domain.model.patterns.factory.FinanceProduct.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.factory;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public abstract class FinanceProduct implements IFinanceProduct {

	private String name;
	private long amount;

}///:~