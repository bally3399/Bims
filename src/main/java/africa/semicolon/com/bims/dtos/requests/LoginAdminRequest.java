package africa.semicolon.com.bims.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginAdminRequest {
    private String email;
    private String password;

}
