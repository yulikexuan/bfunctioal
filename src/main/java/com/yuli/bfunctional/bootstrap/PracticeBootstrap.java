//: com.yuli.bfunctional.bootstrap.PracticeBootstrap.java


package com.yuli.bfunctional.bootstrap;


import com.yuli.bfunctional.j8ia.client.ch05.PuttingIntoPractice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class PracticeBootstrap implements
        ApplicationListener<ContextRefreshedEvent> {

    private final PuttingIntoPractice puttingIntoPractice;

    @Autowired
    public PracticeBootstrap(PuttingIntoPractice puttingIntoPractice) {
        this.puttingIntoPractice = puttingIntoPractice;
    }

    @Override
    public void onApplicationEvent(
            ContextRefreshedEvent contextRefreshedEvent) {

        this.puttingIntoPractice.query_1();
    }

}///:~