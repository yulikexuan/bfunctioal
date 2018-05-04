//: com.yuli.bfunctional.j8ia.domain.model.generics.ProducerTest.java


package com.yuli.bfunctional.j8ia.domain.model.generics;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class ProducerTest {

	private Producer producer;

	@Before
	public void setUp() throws Exception {
		this.producer = new Producer();
		List<C2> c2List = new ArrayList<>();
		c2List.add(new C2());
		c2List.add(new D1());
		c2List.add(new D2());
		c2List.add(new E1());
		c2List.add(new E2());
		c2List.add(new E3());
		c2List.add(new E4());
		this.producer.setElements(c2List);
	}

	@Test
	public void able_To_Produce_Elements_Whose_Super_Class_Is_C2() throws Exception {

		// Given
		List<? extends C2> elements = this.producer.getElements();

		// When
		boolean notC2 = elements.stream()
				.anyMatch(e -> !(e instanceof C2));

		// Then
		assertThat(notC2, is(false));
	}

	@Test
	public void not_Able_To_Add_Any_New_Element_After_Init() throws Exception {

		// Given
		List<? extends C2> elements = this.producer.getElements();

		// When
		// elements.add(new C2());
		// elements.add(new D1());
		// elements.add(new D2());
	}

	@Test
	public void compatible_With_D1_List() throws Exception {

		// Given
		List<D1> d1List = new ArrayList<>();
		d1List.add(new D1());
		d1List.add(new E1());
		d1List.add(new E2());
		this.producer.setElements(d1List);

		// When
		boolean notD1 = this.producer.getElements().stream()
				.anyMatch(e -> !(e instanceof D1));

		// Then
		assertThat(notD1, is(false));
	}

}///:~