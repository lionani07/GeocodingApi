package app.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import static java.lang.String.format;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String streetName;

    @NotBlank
    private String number;

    private String complement;

    @NotBlank
    private String neighbourhood;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String zipcode;

    private String latitude;

    private String longitude;

    public String geolocationFormat() {
        return format("%s, %s, %s, %s, %s",
                this.number,
                this.streetName,
                this.city,
                this.state,
                this.country);
    }

}


