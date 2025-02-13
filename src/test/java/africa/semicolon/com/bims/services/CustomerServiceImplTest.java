//package africa.semicolon.com.bims.services;
//
//import africa.semicolon.com.bims.data.model.Customer;
//import africa.semicolon.com.bims.data.model.Design;
//import africa.semicolon.com.bims.data.repository.CustomerRepository;
//import africa.semicolon.com.bims.dtos.requests.AddDesignRequest;
//import africa.semicolon.com.bims.dtos.requests.AddItemToCartRequest;
//import africa.semicolon.com.bims.dtos.requests.RegisterAdminRequest;
//import africa.semicolon.com.bims.dtos.requests.SearchForProductByNameRequest;
//import jakarta.mail.MessagingException;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class CustomerServiceImplTest {
//
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private OrderService orderService;
//    @Autowired
//    private CustomerRepository customers;
//    @Autowired
//    private AdminServices adminServices;
//    @Autowired
//    private DesignService productService;
//    @Autowired
//    private CartServices cartServices;
//
//    private RegisterAdminRequest registerAdminRequest;
//
//
//    @Test
//    public void testThatCustomerCanAddItemToCart() throws IOException, MessagingException {
//        AddDesignRequest addRequest = new AddDesignRequest();
//        addRequest.setProductName("productName");
////        addRequest.se(BigDecimal.valueOf(2000));
////        addRequest.setFile;
//
//        SearchForProductByNameRequest searchRequest = new SearchForProductByNameRequest();
//        searchRequest.setProductName(addRequest.getProductName());
//
//        Design product = productService.findById(adminServices.addProductToStore(addRequest).getId());
//        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest();
//        addItemToCartRequest.setProductId(product.getId());
//        addItemToCartRequest.setCustomerId(customers.);
//        addItemToCartRequest.setQuantity(2);
//
////        assertEquals(1, cartServices.countCustomerItem(customer.getId()));
//
//    }
//    @Test
//    public void testThatCustomerCanAddMoreItemToCart() throws IOException, MessagingException {
//
//        AddDesignRequest addRequest = new AddDesignRequest();
//        addRequest.setProductName("productName");
////        addRequest.setProductDescription("Product_description");
////        addRequest.setProductPrice(BigDecimal.valueOf(2000));
////        addRequest.setCategory(ProductCategory.CLOTHING);
//
//        AddDesignRequest addRequest1 = new AddDesignRequest();
//        addRequest1.setProductName("productName");
////        addRequest1.setProductDescription("Product_description");
////        addRequest1.setProductPrice(BigDecimal.valueOf(2000));
////        addRequest1.setCategory(ProductCategory.CLOTHING);
//
//        SearchForProductByNameRequest searchRequest = new SearchForProductByNameRequest();
//        searchRequest.setProductName(addRequest.getProductName());
//
//        Design product = productService.findById(adminServices.addProductToStore(addRequest).getId());
//        Design product1 = productService.findById(adminServices.addProductToStore(addRequest1).getId());
//
//        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest();
//        addItemToCartRequest.setProductId(product.getId());
////        addItemToCartRequest.setCustomerId(customer.getId());
//        addItemToCartRequest.setQuantity(2);
//
//        AddItemToCartRequest addItemToCartRequest1 = new AddItemToCartRequest();
//        addItemToCartRequest1.setProductId(product1.getId());
////        addItemToCartRequest1.setCustomerId(customer.getId());
//        addItemToCartRequest1.setQuantity(3);
//
//        cartServices.addItemToCart(addItemToCartRequest);
//        cartServices.addItemToCart(addItemToCartRequest1,customer);
//
//        assertEquals(2,cartServices.countCustomerItem(customer.getId()));
//        customers.delete(customerService.findCustomer(request.getCustomerEmail()));
//    }
//    @Test public void testThatCustomerCanRemoveFromCart() throws IOException, MessagingException {
//        customerService.registerCustomer(request);
//        Customer customer = customerService.findCustomer(request.getCustomerEmail());
//
//
//        AddProductRequest addRequest = new AddProductRequest();
//        addRequest.setProductName("productName");
//        addRequest.setProductDescription("Product_description");
//        addRequest.setProductPrice(BigDecimal.valueOf(2000));
//        addRequest.setCategory(ProductCategory.CLOTHING);
//
//        AddProductRequest addRequest1 = new AddProductRequest();
//        addRequest1.setProductName("productName");
//        addRequest1.setProductDescription("Product_description");
//        addRequest1.setProductPrice(BigDecimal.valueOf(2000));
//        addRequest1.setCategory(ProductCategory.CLOTHING);
//
//        SearchForProductByNameRequest searchRequest = new SearchForProductByNameRequest();
//        searchRequest.setProductName(addRequest.getProductName());
//
//        Product product = productService.findProductBy(adminServices.addProductToStore(addRequest).getProductId());
//        Product product1 = productService.findProductBy(adminServices.addProductToStore(addRequest1).getProductId());
//
//        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest();
//        addItemToCartRequest.setProductId(product.getId());
//        addItemToCartRequest.setCustomerId(customer.getId());
//        addItemToCartRequest.setQuantity(2);
//
//        AddItemToCartRequest addItemToCartRequest1 = new AddItemToCartRequest();
//        addItemToCartRequest1.setProductId(product1.getId());
//        addItemToCartRequest1.setCustomerId(customer.getId());
//        addItemToCartRequest1.setQuantity(3);
//
//        AddItemResponse addItemResponse =cartServices.addItemToCart(addItemToCartRequest,customer);
//        customerService.addItemToCart(addItemToCartRequest1,customer.getId());
//
//        RemoveItemFromCartRequest removeItemFromCartRequest = new RemoveItemFromCartRequest();
//        removeItemFromCartRequest.setItemId(addItemResponse.getItemId());
//        cartServices.removeItemFromCart(removeItemFromCartRequest,customer);
//
//        assertEquals(1,cartServices.countCustomerItem(customer.getId()));
//        customers.delete(customerService.findCustomer(request.getCustomerEmail()));
//    }
//    @Test
//    public void testCustomerCanViewCart() throws IOException, MessagingException {
//        customerService.registerCustomer(request);
//        Customer customer = customerService.findCustomer(request.getCustomerEmail());
//
//
//        AddProductRequest addRequest = new AddProductRequest();
//        addRequest.setProductName("productName");
//        addRequest.setProductDescription("Product_description");
//        addRequest.setProductPrice(BigDecimal.valueOf(2000));
//        addRequest.setCategory(ProductCategory.CLOTHING);
//
//        AddProductRequest addRequest1 = new AddProductRequest();
//        addRequest1.setProductName("productName");
//        addRequest1.setProductDescription("Product_description");
//        addRequest1.setProductPrice(BigDecimal.valueOf(2000));
//        addRequest1.setCategory(ProductCategory.CLOTHING);
//
//        SearchForProductByNameRequest searchRequest = new SearchForProductByNameRequest();
//        searchRequest.setProductName(addRequest.getProductName());
//
//        Product product = productService.findProductBy(adminServices.addProductToStore(addRequest).getProductId());
//        Product product1 = productService.findProductBy(adminServices.addProductToStore(addRequest1).getProductId());
//
//        AddItemToCartRequest addItemToCartRequest = new AddItemToCartRequest();
//        addItemToCartRequest.setProductId(product.getId());
//        addItemToCartRequest.setCustomerId(customer.getId());
//        addItemToCartRequest.setQuantity(2);
//
//        AddItemToCartRequest addItemToCartRequest1 = new AddItemToCartRequest();
//        addItemToCartRequest1.setProductId(product1.getId());
//        addItemToCartRequest1.setCustomerId(customer.getId());
//        addItemToCartRequest1.setQuantity(3);
//
//        customerService.addItemToCart(addItemToCartRequest,customer.getId());
//        customerService.addItemToCart(addItemToCartRequest1,customer.getId());
//
//        assertEquals(2,cartServices.viewCart(customer.getId()).size());
//
//        customers.delete(customerService.findCustomer(request.getCustomerEmail()));
//    }
//
//
//}