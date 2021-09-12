package app.template;

import app.model.Address;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AddressTemplates {

    public Address.AddressBuilder defaultBuilder() {
        return Address.builder()
                .streetName("streetName")
                .number("16")
                .neighbourhood("neighbourhood")
                .city("Taubate")
                .state("SP")
                .country("Brasil")
                .zipcode("12062-200");
    }
}
