//: com.yuli.bfunctional.j8ia.domain.model.generics.Producer.java


package com.yuli.bfunctional.j8ia.domain.model.generics;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class Producer {

    /*
     * The purpose of 'List<? extends C2> elements' is to produce elements
     * whose super class must be C2
     */
    private List<? extends C2> elements = new ArrayList<C2>();

}///:~