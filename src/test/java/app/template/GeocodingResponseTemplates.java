package app.template;

import app.adapter.response.GeocodingResponse;
import app.adapter.response.Geometry;
import app.adapter.response.Location;
import app.adapter.response.Result;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class GeocodingResponseTemplates {

    public GeocodingResponse.GeocodingResponseBuilder defaultBuilder() {
        return GeocodingResponse
                .builder()
                .status("OK")
                .results(List.of(result()));
    }

    private Result result() {
        return Result
                .builder()
                .geometry(geometry())
                .build();
    }

    private Geometry geometry() {
        return Geometry
                .builder()
                .location(location())
                .build();
    }

    private Location location() {
        return Location
                .builder()
                .lat("-22.9987283")
                .lng("-45.5430516")
                .build();
    }
}
