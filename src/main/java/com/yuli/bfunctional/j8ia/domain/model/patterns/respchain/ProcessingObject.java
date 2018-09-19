//: com.yuli.bfunctional.j8ia.domain.model.patterns.respchain.ProcessingObject.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.respchain;


import java.util.function.UnaryOperator;


public class ProcessingObject<T> {

    private final UnaryOperator<T> operator;

    private ProcessingObject<T> successor;

    public ProcessingObject(UnaryOperator<T> operator) {
        this.operator = operator;
    }

    public void setSuccessor(ProcessingObject<T> successor) {
        this.successor = successor;
    }

    public T handle(T input) {

        T r = this.operator.apply(input);

        if (this.successor != null) {
            return this.successor.handle(r);
        }

        return r;
    }

}///:~