package com.liverpool.aggregator.config;

import com.liverpool.aggregator.client.CustomerClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpClientConfig {

    @Value("${services.users.url}")
    private String usersUrl;

    @Value("${services.products.url}")
    private String productsUrl;

    @Value("${services.orders.url}")
    private String ordersUrl;


    private <T> T buildClient(String url, Class<T> clientClass) {
        RestClient restClient = RestClient.builder()
                .baseUrl(url)
                .defaultStatusHandler(
                        HttpStatusCode::isError,
                        (request, response) -> {
                            throw new HttpClientErrorException(response.getStatusCode());
                        }
                ).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(clientClass);
    }
}
