//: com.yuli.bfunctional.j8ia.domain.model.patterns.factory.LambdaProductFactory.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.factory;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class LambdaProductFactory implements IProductFactory {

	private static final Map<String, Supplier<IFinanceProduct>> F_MAP =
			new HashMap<>();

	static {
		F_MAP.put("loan", Loan::new);
		F_MAP.put("stock", Stock::new);
		F_MAP.put("bond", Bond::new);
	}

	@Override
	public IFinanceProduct createProduct(String name) {

		Supplier<IFinanceProduct> fpSupplier = F_MAP.get(name);

		if (fpSupplier != null) {
			return fpSupplier.get();
		}

		throw new RuntimeException("No such product " + name);
	}

}///:~