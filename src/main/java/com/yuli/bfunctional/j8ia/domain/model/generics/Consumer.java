//: com.yuli.bfunctional.j8ia.domain.model.generics.Consumer.java


package com.yuli.bfunctional.j8ia.domain.model.generics;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class Consumer {

    /*
     * The purpose of 'List<? super C2> elements' is to let the client of
     * elements add new elenent whose super class is C2.
     */ List<? super C2> elements = new ArrayList<C2>();

}///:~