package africa.semicolon.com.bims.services;

import africa.semicolon.com.bims.data.model.Design;
import africa.semicolon.com.bims.dtos.requests.AddDesignRequest;
import africa.semicolon.com.bims.dtos.requests.DeleteDesignRequest;
import africa.semicolon.com.bims.dtos.requests.LoginRequest;
import africa.semicolon.com.bims.dtos.requests.RegisterAdminRequest;
import africa.semicolon.com.bims.dtos.responses.*;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.io.IOException;
import java.util.List;

public interface AdminServices {
    RegisterAdminResponse register(RegisterAdminRequest request);
    LoginAdminResponse login(LoginRequest request);
    Design addProductToStore(AddDesignRequest addProduct) throws IOException;

    DeleteDesignResponse deleteDesign(DeleteDesignRequest deleteProductRequest);

    EditProductResponse editProduct(Long id, JsonPatch patch) throws JsonPatchException;

    long count();

    String deactivateAccount(Long id);

    void deleteAll();

    FindAdminResponse findAdmin(String email);

    List<AddProductResponse> findAllProductAdded();

}
