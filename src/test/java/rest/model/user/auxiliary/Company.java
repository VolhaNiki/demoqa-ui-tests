package rest.model.user.auxiliary;

import lombok.Data;

@Data
public class Company{
    private Address address;
    private String name;
    private String department;
    private String title;
}