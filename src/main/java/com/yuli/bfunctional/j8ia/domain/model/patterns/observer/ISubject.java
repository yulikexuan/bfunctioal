//: com.yuli.bfunctional.j8ia.domain.model.patterns.observer.ISubject.java


package com.yuli.bfunctional.j8ia.domain.model.patterns.observer;


public interface ISubject<T> {

    void registerObserver(IObserver<T> o);

    void notifyObservers(T t);

}///:~