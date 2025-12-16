package rest.model.company;

import lombok.Data;

@Data
public class Address{
    private String country;
    private String address;
    private String city;
    private String postalCode;
    private Coordinates coordinates;
    private String stateCode;
    private String state;
}