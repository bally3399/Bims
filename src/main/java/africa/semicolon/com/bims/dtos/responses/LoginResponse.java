package africa.semicolon.com.bims.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String message;
    private String jwtToken;
    private String role;
    private String firstName;
    private String lastName;

}
