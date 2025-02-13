package africa.semicolon.com.bims.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterAdminRequest {
    private String email;
    private String password;
    private String username;
    private String phoneNumber;

}
