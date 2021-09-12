package app.adapter.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private String lat;
    private String lng;
}
