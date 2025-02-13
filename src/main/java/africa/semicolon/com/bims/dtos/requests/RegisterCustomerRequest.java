package africa.semicolon.com.bims.dtos.requests;

import africa.semicolon.com.bims.data.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterCustomerRequest {
    @NotNull(message = "name can't be null")
    private String customerName;
    @NotNull(message = "email can't be null")
    private String customerEmail;
    @NotNull(message = "phone number can't be null ")
    private String customerPhone;
    @NotNull(message = "provide a password ")
    private String password;
    @NotNull(message = "role can't be null")
    private Role role;

}
