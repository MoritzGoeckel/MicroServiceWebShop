/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.models.Category;
import hska.microServiceWebShop.models.CategoryQuery;
import hska.microServiceWebShop.models.Error;
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

@Api(value = "categories", description = "the categories API")
public interface CategoriesApi {

    @ApiOperation(value = "Adds a new category", nickname = "addCategory", notes = "", response = Category.class, tags={ "categories", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Category.class),
        @ApiResponse(code = 400, message = "bad request", response = Error.class),
        @ApiResponse(code = 409, message = "conflict", response = Error.class) })
    @RequestMapping(value = "/categories",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Category> addCategory(@ApiParam(value = "The name of the category" ,required=true )  @Valid @RequestBody Category name);


    @ApiOperation(value = "Deletes a category", nickname = "deleteCategory", notes = "", tags={ "categories", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation"),
        @ApiResponse(code = 404, message = "not found", response = Error.class) })
    @RequestMapping(value = "/categories/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteCategory(@ApiParam(value = "The id of the to be deleted category",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "Retrieves a category", nickname = "getCategory", notes = "", response = Category.class, tags={ "categories", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Category.class),
        @ApiResponse(code = 404, message = "not found", response = Error.class) })
    @RequestMapping(value = "/categories/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity<Category> getCategory(@ApiParam(value = "The id of the to be retrieved category",required=true) @PathVariable("id") Long id);


    @ApiOperation(value = "Queries categories. If no name is provided all categories will be returned", nickname = "queryCategories", notes = "", response = Category.class, responseContainer = "List", tags={ "categories", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Successful operation", response = Category.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "not found", response = Error.class)
    })
    @RequestMapping(value = "/categories",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.GET)
    ResponseEntity queryCategories(@ApiParam(value = "Parameters of the categoryquery"  )  @Valid @RequestBody CategoryQuery query);

}
