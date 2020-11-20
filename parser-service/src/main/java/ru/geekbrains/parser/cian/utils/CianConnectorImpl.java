package ru.geekbrains.parser.cian.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.entity.system.Proxy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

@Slf4j
@Component
public class CianConnectorImpl implements CianConnector {

    @Value("${cian-parser.name}")
    private String parserName;

    private final ProxyGate proxyGate;

    @Autowired
    public CianConnectorImpl(ProxyGate proxyGate) {
        this.proxyGate = proxyGate;
    }

    @Override
    public Optional<String> getHtmlPage(URIBuilder uri) {

        Proxy proxy = proxyGate.getProxy();
//                *********** org.apache.http connection- 2st variant*****************
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(proxy.getHost(), Integer.parseInt(proxy.getPort())),
                new UsernamePasswordCredentials(proxy.getLogin(), proxy.getPassword())
        );
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();

        HttpGet request;
        String htmlString = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;

        try {
            request = new HttpGet(uri.build());
            request.addHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36");
            HttpHost httpProxy = new HttpHost(proxy.getHost(), Integer.parseInt(proxy.getPort()), "http");
            RequestConfig config = RequestConfig.custom()
                    .setProxy(httpProxy)
                    .build();
            request.setConfig(config);
            response = httpClient.execute(request);
            entity = response.getEntity();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        if (entity != null) {
            try {
                htmlString = EntityUtils.toString(entity);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (htmlString != null && htmlString.contains("Captcha")) {
                log.warn("Proxy " + proxy.getAddress() + " has been banned from " + this.getName());
                proxyGate.setProxyBlockedBy(this.getName(), proxy);
            }
            return Optional.ofNullable(htmlString);
        }
        return Optional.empty();
    }

    public String getName() {
        return parserName;
    }
}
