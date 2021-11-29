package com.example.testrest;

import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Scope("singleton")
public class TestAnswer {

    private Map<String, String> map = new HashMap<>();
    private JSONObject jsonObject;

    public TestAnswer(){
        map.put("class", "tsub");
        map.put("day", "Monday.29.11.2011");
        jsonObject = new JSONObject(map);
    }

    @Override
    public String toString() {
        return jsonObject.toString();
    }
}
