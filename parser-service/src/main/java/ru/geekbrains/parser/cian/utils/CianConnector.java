package ru.geekbrains.parser.cian.utils;

import org.apache.http.client.utils.URIBuilder;

import java.util.Optional;

public interface CianConnector {
    Optional<String> getHtmlPage(URIBuilder uri);
}
