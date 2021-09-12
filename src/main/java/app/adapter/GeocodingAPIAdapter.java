package app.adapter;

import app.model.Address;
import org.springframework.stereotype.Component;

@Component
public class GeocodingAPIAdapter {

    public GeocodingResponse findLatitudeAndLongitude(Address address) {
        return new GeocodingResponse("", "");
    }
}
