package ru.geekbrains.parser.cian.utils;

import org.jsoup.nodes.Element;
import ru.geekbrains.entity.Address;
import ru.geekbrains.parser.cian.CianApartment;

import java.math.BigDecimal;

public interface DataExtractor {

    CianApartment buildApartment(Element adTag);
}
