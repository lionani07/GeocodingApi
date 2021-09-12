package app.adapter;

import app.adapter.response.GeocodingResponse;
import app.exception.ClientException;
import app.model.Address;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GeocodingClient {

    @Value("${geolocation.url}")
    private String url;

    @Value("${geolocation.key}")
    private String key;

    @Value("${geolocation.format}")
    private String format;

    public Mono<GeocodingResponse> findLatitudeAndLongitude(final Address address) {
        return WebClient.create(url)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(format)
                        .queryParam("address", address.geolocationFormat())
                        .queryParam("key", key)
                        .build()
                )
                .retrieve()
                .onStatus(HttpStatus::isError, clientResponse -> Mono.error(new ClientException("A client error ocurred")))
                .bodyToMono(GeocodingResponse.class);
    }

}
