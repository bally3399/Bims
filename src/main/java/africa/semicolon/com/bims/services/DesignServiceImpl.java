package africa.semicolon.com.bims.services;

import africa.semicolon.com.bims.data.model.Design;
import africa.semicolon.com.bims.data.repository.DesignRepository;
import africa.semicolon.com.bims.dtos.requests.AddDesignRequest;
import africa.semicolon.com.bims.dtos.requests.DeleteDesignRequest;
import africa.semicolon.com.bims.dtos.responses.AddProductResponse;
import africa.semicolon.com.bims.dtos.responses.DeleteDesignResponse;
import africa.semicolon.com.bims.dtos.responses.EditProductResponse;
import africa.semicolon.com.bims.exceptions.DesignNotFoundException;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class DesignServiceImpl implements DesignService {
    private final DesignRepository designRepository;
    private final ModelMapper modelMapper;
    private final Cloudinary cloudinary;

    @Override
    public Design createDesign(AddDesignRequest createDesignRequest) throws IOException {
        validateProductName(createDesignRequest.getProductName());

        if (createDesignRequest.getFile() == null || createDesignRequest.getFile().isEmpty()) {
            throw new IllegalArgumentException("File must be provided");
        }

        Map map = ObjectUtils.asMap("resource_type", "image");
        Map<?, ?> response = cloudinary.uploader().upload(createDesignRequest.getFile().getBytes(), map);
        String url = response.get("url").toString();

        Design design = modelMapper.map(createDesignRequest, Design.class);
        design.setImageUrl(url);

        return designRepository.save(design);
    }


    @Override
    public void deleteAll() {
        designRepository.deleteAll();
    }

    @Override
    public Design findById(Long id) {
        return designRepository.findById(id).orElseThrow(() -> new DesignNotFoundException("Design not found"));
    }

    @Override
    public DeleteDesignResponse deleteDesign(DeleteDesignRequest deleteDesignRequest) {
        Long designId = deleteDesignRequest.getDesign().getId();

        Design design = designRepository.findById(designId)
                .orElseThrow(() -> new DesignNotFoundException("Design not found"));

        designRepository.delete(design);

        DeleteDesignResponse response = new DeleteDesignResponse();
        response.setMessage("Design deleted successfully");
        return response;
    }

    @Override
    public List<Design> get(Long id) {
        return List.of();
    }

    @Override
    public Design findItem(Long itemId) {
        return designRepository.findById(itemId).orElseThrow(()->new DesignNotFoundException("No Item Found"));
    }

    private void validateProductName(String productName) {
        if(productName == null || productName.isBlank()) throw new DesignNotFoundException("Invalid Product. Product Name must not be empty or null");
    }

    @Override
    public EditProductResponse editProduct(Long id, JsonPatch patch) throws JsonPatchException {
        Design design = findById(id);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.convertValue(design, JsonNode.class);
        node = patch.apply(node);
        design = mapper.convertValue(node, Design.class);
        design = designRepository.save(design);
        return this.modelMapper.map(design,EditProductResponse.class);
    }

    @Override
    public List<AddProductResponse> findAll() {
        List<Design> productList = designRepository.findAll();
        List<AddProductResponse> productResponses = productList.stream().map((product) -> modelMapper.map(product,AddProductResponse.class)).toList();
        if(productList.isEmpty())throw new DesignNotFoundException("No Product Found");
        return productResponses;
    }

}
