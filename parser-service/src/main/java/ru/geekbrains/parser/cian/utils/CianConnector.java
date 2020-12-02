package ru.geekbrains.parser.cian.utils;

import org.apache.http.client.utils.URIBuilder;

import java.util.Optional;

/**
 *
 * @author mihailzubarev
 */
public interface CianConnector {
    Optional<String> getHtmlPage(URIBuilder uri);
}
