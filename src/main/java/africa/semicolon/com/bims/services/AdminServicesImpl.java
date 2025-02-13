package africa.semicolon.com.bims.services;

import africa.semicolon.com.bims.data.model.Admin;
import africa.semicolon.com.bims.data.model.Design;
import africa.semicolon.com.bims.data.model.Role;
import africa.semicolon.com.bims.data.repository.AdminRepository;
import africa.semicolon.com.bims.dtos.requests.AddDesignRequest;
import africa.semicolon.com.bims.dtos.requests.DeleteDesignRequest;
import africa.semicolon.com.bims.dtos.requests.LoginRequest;
import africa.semicolon.com.bims.dtos.requests.RegisterAdminRequest;
import africa.semicolon.com.bims.dtos.responses.*;
import africa.semicolon.com.bims.exceptions.EmailExistException;
import africa.semicolon.com.bims.exceptions.PictureUploadFailedException;
import africa.semicolon.com.bims.exceptions.UserNotFoundException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import utils.JwtUtils;

import java.io.IOException;
import java.util.List;


@Service
@AllArgsConstructor
public class AdminServicesImpl implements AdminServices {
    private ModelMapper mapper;
    private AdminRepository admins;
    private DesignService designService;


    @Override
    public RegisterAdminResponse register(RegisterAdminRequest request) {
        validateExistence(request.getEmail());
        try {
            Admin admin = mapper.map(request,Admin.class);
            admin.setRole(Role.ADMIN);
            admin = admins.save(admin);

            String subject = "WELCOME TO SOFT'S STORE";
            return mapper.map(admin, RegisterAdminResponse.class);
        }catch(PictureUploadFailedException e){
            throw new PictureUploadFailedException(e.getMessage());
        }
    }

    private void validateExistence(String email) {
        if(admins.existsByEmail(email)) throw new EmailExistException("Email Already exist");
    }

    @Override
    public LoginAdminResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        return checkLoginDetail(email, password);
    }

    private LoginAdminResponse checkLoginDetail(String email, String password) {
        Admin optionalUser = admins.findByEmail(email);
        if (optionalUser!= null){
            Admin user = optionalUser;
            if (user.getPassword().equals(password)) {
                return loginResponseMapper(user);
            } else {
                throw new UserNotFoundException("Invalid username or password");
            }
        } else {
            throw new UserNotFoundException("Invalid username or password");
        }
    }

    private LoginAdminResponse loginResponseMapper(Admin user) {
        LoginAdminResponse loginResponse = new LoginAdminResponse();
        String accessToken = JwtUtils.generateAccessToken(user.getId());
        BeanUtils.copyProperties(user, loginResponse);
        loginResponse.setJwtToken(accessToken);
        loginResponse.setMessage("Login Successful");
        loginResponse.setRole(user.getRole().toString());
        return loginResponse;
    }


    @Override
    public Design addProductToStore(AddDesignRequest addProduct) throws IOException {
        return designService.createDesign(addProduct);
    }

    @Override
    public DeleteDesignResponse deleteDesign(DeleteDesignRequest deleteProductRequest) {
        return designService.deleteDesign(deleteProductRequest);
    }

    @Override
    public EditProductResponse editProduct(Long id, JsonPatch patch) throws JsonPatchException {
        return designService.editProduct(id, patch);
    }

    @Override
    public long count() {
        return admins.count();
    }

    @Override
    public String deactivateAccount(Long id) {
        admins.deleteById(id);
        return "Account Deactivated";
    }

    @Override
    public void deleteAll() {
         admins.deleteAll();
    }

    @Override
    public FindAdminResponse findAdmin(String email) {
        Admin admin=  admins.findByEmail(email);
        if(admin == null)throw new UserNotFoundException("Admin not found");
        return mapper.map(admin, FindAdminResponse.class);
    }

    @Override
    public List<AddProductResponse> findAllProductAdded() {
        return designService.findAll();
    }

}
