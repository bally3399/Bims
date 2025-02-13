package africa.semicolon.com.bims.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AddDesignRequest {
    @NotNull(message = "Product Name Can't be null")
    private String productName;
    @NotNull(message = "Please add a picture to the product")
    private MultipartFile file;

}
