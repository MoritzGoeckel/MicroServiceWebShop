/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.Product;
import hska.microServiceWebShop.models.ProductQuery;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-03T22:24:38.514Z")

@Api(value = "products", description = "the products API")
public interface ProductsApi {

    @ApiOperation(value = "Adds a new product", nickname = "addProduct", notes = "", response = Product.class, tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Product.class),
        @ApiResponse(code = 400, message = "Invalid input", response = Error.class),
        @ApiResponse(code = 409, message = "Conflict", response = Error.class) })
    @RequestMapping(value = "/products",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Product> addProduct(@ApiParam(value = "The inserted product" ,required=true )  @Valid @RequestBody Product product);


    @ApiOperation(value = "Deletes a product", nickname = "deleteProduct", notes = "", tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation"),
        @ApiResponse(code = 404, message = "not found", response = Error.class) })
    @RequestMapping(value = "/products/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteProduct(@ApiParam(value = "The id of the to be deleted product",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "Retrieves a product", nickname = "getProduct", notes = "", response = Product.class, tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Product.class),
        @ApiResponse(code = 404, message = "not found", response = Error.class) })
    @RequestMapping(value = "/products/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the to be retrieved product",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "Queries products. If no parameters is provided all products will be returned", nickname = "queryProducts", notes = "", response = Product.class, responseContainer = "List", tags={ "products", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Product.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad request", response = Error.class) })
    @RequestMapping(value = "/products",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<List<Product>> queryProducts(@ApiParam(value = "The name of the product"  )  @Valid @RequestBody ProductQuery query);

}
