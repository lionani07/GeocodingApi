package app.adapter.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Location {

    private String lat;
    private String lng;
}
