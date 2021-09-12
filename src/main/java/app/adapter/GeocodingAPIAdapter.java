package app.adapter;

import app.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeocodingAPIAdapter {

    private final GeocodingClient geocodingClient;

    public GeocodingResponse findLatitudeAndLongitude(Address address) {
        return this.geocodingClient.findLatitudeAndLongitude(address).block();
    }
}
