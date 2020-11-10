package ru.geekbrains.service.parserservice;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    public String name;
    private boolean processing = false;
    void start(String country, String city){};
    //исправить Object -> Ad
    Map<String, Object> getResult() {
        return new HashMap<>();
    };
    public boolean getProcessingStatus(){
        return processing;
    }
}