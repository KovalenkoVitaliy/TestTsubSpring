package com.example.testrest;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Scope("singleton")
@PropertySource("classpath:configInflux.properties")
public class InstanseInflux {

    private String URL;
    private String dataBase;
    private InfluxDB influx;
    private String metrics;
    public static volatile int NumberRequest = 0;

    public InstanseInflux(@Value("${influx.URL}") String URL, @Value("${influx.DB}") String dataBase, @Value("${influx.metric}") String metrics){
        this.URL = URL;
        this.dataBase = dataBase;
        this.metrics = metrics;
        influx = InfluxDBFactory.connect(this.URL);
        influx.query(new Query("Create database " + this.dataBase));
        influx.setDatabase(this.dataBase);
    }

    public void writeMetrics(long timeStart, long timeEnd, String operation){
        influx.write(Point.measurement(metrics)
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag("operation", operation)
                .addField("numberRequest", ++NumberRequest)
                .addField("responseTime", timeEnd - timeStart)
                .build());
    }
}
