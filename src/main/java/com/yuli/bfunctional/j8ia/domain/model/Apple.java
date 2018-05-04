//: com.yuli.bfunctional.j8ia.domain.model.Apple.java


package com.yuli.bfunctional.j8ia.domain.model;


import lombok.ToString;


@ToString(callSuper=true)
public class Apple extends Fruit {

    public Apple() {
        super();
    }

    public Apple(String barcode, Color color, int weight) {
        super(barcode, color, weight);
    }

    public static final class Builder {

        private String barcode;
        private Color color;
        private int weight;

        public Builder setBarcode(String barcode) {
            this.barcode = barcode;
            return this;
        }

        public void clear() {
            this.barcode = null;
            this.color = null;
            this.weight = 0;
        }

        public Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Apple create() {
            return new Apple(barcode, color, weight);
        }
    }

}///:~