//: com.yuli.bfunctional.j8ia.domain.model.Fruit.java


package com.yuli.bfunctional.j8ia.domain.model;


import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "barcode")
@ToString(of = {"color", "weight"})
public class Fruit {

    protected String barcode;
    protected Color color;
    protected int weight;

    Fruit(String barcode, Color color, int weight) {
        this.barcode = barcode;
        this.color = color;
        this.weight = weight;
    }

}///:~