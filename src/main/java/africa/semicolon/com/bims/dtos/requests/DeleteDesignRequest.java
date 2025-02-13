package africa.semicolon.com.bims.dtos.requests;

import africa.semicolon.com.bims.data.model.Design;
import lombok.Data;

@Data
public class DeleteDesignRequest {
    private Design design;
}
