package africa.semicolon.com.bims.controller;

import africa.semicolon.com.bims.data.model.Design;
import africa.semicolon.com.bims.dtos.requests.AddDesignRequest;
import africa.semicolon.com.bims.dtos.requests.DeleteDesignRequest;
import africa.semicolon.com.bims.dtos.requests.LoginRequest;
import africa.semicolon.com.bims.dtos.requests.RegisterAdminRequest;
import africa.semicolon.com.bims.dtos.responses.*;
import africa.semicolon.com.bims.exceptions.EmailExistException;
import africa.semicolon.com.bims.services.AdminServices;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@Validated
@RequestMapping("/api/v1/Admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private AdminServices adminServices;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterAdminRequest request){
        try {
            RegisterAdminResponse result = adminServices.register(request);
            return ResponseEntity.status(CREATED).body(result);
        } catch(EmailExistException e){
            return ResponseEntity.status(HttpStatus.valueOf(e.getMessage())).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<LoginAdminResponse> login(@RequestBody @Valid LoginRequest request)  {
        var result = adminServices.login(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping( value = "/add_product", consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Design> addProductToStore(@ModelAttribute AddDesignRequest addProduct) throws IOException {
        var result = adminServices.addProductToStore(addProduct);
        return ResponseEntity.status(CREATED).body(result);
    }
    @PatchMapping("/edit_product")
    public ResponseEntity<EditProductResponse> editProduct(@RequestBody Long id, @RequestBody JsonPatch patch) throws JsonPatchException {
        var result = adminServices.editProduct(id, patch);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/delete_product")
    public ResponseEntity<?> deleteProduct(@RequestBody DeleteDesignRequest deleteProductRequest){
        var result = adminServices.deleteDesign(deleteProductRequest);
        return ResponseEntity.ok(result);
    }
   @DeleteMapping("/de_activate_acc/{id}")
    public ResponseEntity<?> deActivateAcc(@PathVariable("id") Long id){
        var result = adminServices.deactivateAccount(id);
        return ResponseEntity.ok(result);

   }
   @GetMapping("/find_admin/")
    public ResponseEntity<FindAdminResponse> findAdmin(@Param("email") String email){
        var result = adminServices.findAdmin(email);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/find_all_product_added")
    public ResponseEntity<List<AddProductResponse>> findAllProductAdded(){
        var result = adminServices.findAllProductAdded();
        return ResponseEntity.ok(result);
    }
}
