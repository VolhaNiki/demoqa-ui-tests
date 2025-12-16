package rest.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPojo{
    private Integer id;
    private String username;
    private String lastName;
    private String firstName;
    private String phone;
    private String email;
}