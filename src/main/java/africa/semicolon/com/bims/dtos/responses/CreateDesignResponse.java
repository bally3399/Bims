package africa.semicolon.com.bims.dtos.responses;

import africa.semicolon.com.bims.data.model.Design;
import lombok.Data;

@Data
public class CreateDesignResponse {
    private String message;
    private Design design;

}
