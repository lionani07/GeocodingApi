package app.adapter.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
