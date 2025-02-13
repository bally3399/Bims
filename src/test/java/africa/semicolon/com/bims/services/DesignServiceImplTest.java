package africa.semicolon.com.bims.services;

import africa.semicolon.com.bims.data.model.Design;
import africa.semicolon.com.bims.dtos.requests.AddDesignRequest;
import africa.semicolon.com.bims.dtos.requests.DeleteDesignRequest;
import africa.semicolon.com.bims.dtos.responses.CreateDesignResponse;
import africa.semicolon.com.bims.dtos.responses.DeleteDesignResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DesignServiceImplTest {
    @Autowired
    private DesignService designService;

    private CreateDesignResponse response;

    @BeforeEach
    public void setUp() throws IOException {
        designService.deleteAll();

        AddDesignRequest createDesignRequest = new AddDesignRequest();
        createDesignRequest.setProductName("Agbada");
//        createDesignRequest.setFile("gggggg");
//        response = designService.createDesign(createDesignRequest);
    }

    @Test
    public void createDesign() {
        assertThat(response.getMessage()).isEqualTo("Design created successfully");
    }

    @Test
    public void findDesign(){
        Design design = designService.findById(response.getDesign().getId());
        assertThat(design.getId()).isEqualTo(response.getDesign().getId());
    }
    @Test
    public void removeDesign(){
        Design design = designService.findById(response.getDesign().getId());
        DeleteDesignRequest deleteDesignRequest = new DeleteDesignRequest();
        deleteDesignRequest.setDesign(design);

        DeleteDesignResponse deleteDesignResponse = designService.deleteDesign(deleteDesignRequest);
        assertThat(deleteDesignResponse.getMessage()).isEqualTo("Design deleted successfully");

    }
}