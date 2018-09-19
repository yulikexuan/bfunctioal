//: com.yuli.bfunctional.j8ia.domain.model.optional.Insurance.java


package com.yuli.bfunctional.j8ia.domain.model.optional;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Insurance {

    private String name;

    public Insurance(String name) {
        this.name = name;
    }

}///:~