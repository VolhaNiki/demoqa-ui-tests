package rest.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rest.model.user.auxiliary.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFullPojo{
    private String lastName;
    private String role;
    private String gender;
    private String university;
    private String maidenName;
    private String ein;
    private String ssn;
    private String bloodGroup;
    private String password;
    private Hair hair;
    private Bank bank;
    private String eyeColor;
    private Company company;
    private Integer id;
    private String email;
    private Double height;
    private String image;
    private Address address;
    private String ip;
    private Double weight;
    private String userAgent;
    private String birthDate;
    private Crypto crypto;
    private String firstName;
    private String macAddress;
    private String phone;
    private Integer age;
    private String username;
}