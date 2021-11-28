//package com.example.testrest;
//
//import org.influxdb.InfluxDB;
//import org.influxdb.InfluxDBFactory;
//import org.influxdb.dto.Point;
//import org.influxdb.dto.Query;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//import java.util.Random;
//import java.util.concurrent.TimeUnit;
//
//@RestController
//@Scope("singleton")
//public class Controller {
//
//    public static Logger logger = LoggerFactory.getLogger(Controller.class);
//    public static volatile int NumberRequest = 0;
//    InfluxDB influxDB;
//
//    @PostConstruct
//    public void init(){
//        String URL = "http://localhost:8086";
//        influxDB = InfluxDBFactory.connect(URL);
//        String dataBase = "Rest";
//        influxDB.query(new Query("Create database " + dataBase));
//        influxDB.setDatabase(dataBase);
//    }
//
//    @Autowired
//    TestAnswer testAnswer;
//
//    @Autowired
//    InstanseInflux instanseInflux;
//
//    @RequestMapping("/sender")
//    public String sender(){
//        long timeStart = System.currentTimeMillis();
//        logger.info("SENDER send message");
//        long delay = new Random().nextInt(500);
//        try {
//            Thread.sleep(1000 + delay);
//        } catch (InterruptedException e) {
//        }
//        long timeEnd = System.currentTimeMillis();
//        influxDB.write(Point.measurement("metrics")
//                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//                .tag("operation", "sender")
//                .addField("numberRequest", ++NumberRequest)
//                .addField("responseTime", timeEnd - timeStart)
//                .build());
//        return testAnswer.toString();
//    }
//
//    @RequestMapping("/ping")
//    public String testServer(){
//        long timeStart = System.currentTimeMillis();
//        logger.info("PING send message");
//        long delay = new Random().nextInt(200);
//        try {
//            Thread.sleep(1000 + delay);
//        } catch (InterruptedException e) {
//        }
//        long timeEnd = System.currentTimeMillis();
//        influxDB.write(Point.measurement("metrics")
//                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
//                .tag("operation", "ping")
//                .addField("numberRequest", ++NumberRequest)
//                .addField("responseTime", timeEnd - timeStart)
//                .build());
//        return "status 200";
//    }
//}
