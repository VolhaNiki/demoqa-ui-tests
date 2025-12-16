package rest.model.user.auxiliary;

import lombok.Data;

@Data
public class Bank{
    private String iban;
    private String cardExpire;
    private String cardType;
    private String currency;
    private String cardNumber;
}