package com.example.testrest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;


@RestController
@Scope("singleton")
public class ControllerRest {

    public static Logger logger = LoggerFactory.getLogger(ControllerRest.class);

    @Autowired
    TestAnswer testAnswer;

    @Autowired
    InstanseInflux instanseInflux;

    @RequestMapping("/sender")
    public String sender(){
        long timeStart = System.currentTimeMillis();
        logger.info("SENDER send message");
        long delay = new Random().nextInt(500);
        try {
            Thread.sleep(1000 + delay);
        } catch (InterruptedException e) {
        }
        long timeEnd = System.currentTimeMillis();
       instanseInflux.writeMetrics(timeStart, timeEnd, "sender");
        return testAnswer.toString();
    }

    @RequestMapping("/ping")
    public String testServer(){
        long timeStart = System.currentTimeMillis();
        logger.info("PING send message");
        long delay = new Random().nextInt(200);
        try {
            Thread.sleep(1000 + delay);
        } catch (InterruptedException e) {
        }
        long timeEnd = System.currentTimeMillis();
        instanseInflux.writeMetrics(timeStart, timeEnd, "ping");
        return "status 200";
    }
}
