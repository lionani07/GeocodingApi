package app.adapter.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GeocodingResponse {

    private String status;

    private List<Result> results;

    public String getLongitude() {
       return getLocation().getLng();
    }

    public String getLatitude() {
        return getLocation().getLat();
    }

    private Location getLocation() {
        return results.get(0).getGeometry().getLocation();
    }
}
