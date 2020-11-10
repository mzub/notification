package ru.geekbrains.model;

import lombok.Data;
import ru.geekbrains.parser.ApartmentParserInterface;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public abstract class Parser {

    private boolean processing = false;

    private List<ApartmentParserInterface> apartmentList;

    public abstract void start(String country, String city);

    public abstract String getName();


    public List<ApartmentParserInterface> getResult() {
        return this.apartmentList;
    }

    public boolean getProcessingStatus() {
        return processing;
    }
}