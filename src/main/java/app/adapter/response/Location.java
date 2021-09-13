package app.adapter.response;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private String lat;
    private String lng;
}
