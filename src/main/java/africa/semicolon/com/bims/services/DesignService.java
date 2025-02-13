package africa.semicolon.com.bims.services;

import africa.semicolon.com.bims.data.model.Design;
import africa.semicolon.com.bims.dtos.requests.AddDesignRequest;
import africa.semicolon.com.bims.dtos.requests.DeleteDesignRequest;
import africa.semicolon.com.bims.dtos.responses.AddProductResponse;
import africa.semicolon.com.bims.dtos.responses.DeleteDesignResponse;
import africa.semicolon.com.bims.dtos.responses.EditProductResponse;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface DesignService {
    Design createDesign(AddDesignRequest createDesignRequest) throws IOException;

    void deleteAll();

    Design findById(Long id);

    DeleteDesignResponse deleteDesign(DeleteDesignRequest deleteDesignRequest);

    List<Design> get(Long id);

    Design findItem(Long itemId);

    EditProductResponse editProduct(Long id, JsonPatch patch) throws JsonPatchException;

    List<AddProductResponse> findAll();
}
