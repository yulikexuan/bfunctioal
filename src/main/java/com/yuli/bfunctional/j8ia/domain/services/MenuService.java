//: com.yuli.bfunctional.j8ia.domain.services.MenuService.java


package com.yuli.bfunctional.j8ia.domain.services;


import com.yuli.bfunctional.j8ia.domain.model.streams.Dish;
import com.yuli.bfunctional.j8ia.domain.repositories.IMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MenuService implements IMenuService {

	private final IMenuRepository menuRepository;

	@Autowired
	public MenuService(IMenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public List<Dish> getMenu() {
		return this.menuRepository.getMenu();
	}

}///:~