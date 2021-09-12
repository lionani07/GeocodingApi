package app.adapter;

import app.adapter.response.GeocodingResponse;
import app.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeocodingApiAdapter {

    private final GeocodingClient geocodingClient;

    public GeocodingResponse findLatitudeAndLongitude(final Address address) {
        return this.geocodingClient.findLatitudeAndLongitude(address).block();
    }
}
