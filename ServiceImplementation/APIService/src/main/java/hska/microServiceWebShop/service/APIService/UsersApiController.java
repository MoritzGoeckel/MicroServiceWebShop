package hska.microServiceWebShop.service.APIService;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.User;
import hska.microServiceWebShop.models.UserQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T00:32:34.965Z")

@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);
    hska.microServiceWebShop.api.UserRoleApi userRoleAPIClient = new hska.microServiceWebShop.api.UserRoleApi();

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity createUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User user) {
        String accept = request.getHeader("Accept");
        try {
            userRoleAPIClient.createUser(user);
            return ResponseEntity.ok().body(user);
        } catch (ApiException e) {
            e.printStackTrace();
            if(e.getResponseBody() == null)
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return ResponseEntity.status(e.getCode()).body(e.getResponseBody());
        }
    }

    public ResponseEntity deleteUser(@ApiParam(value = "",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        try {
            User u = userRoleAPIClient.getUserById(id);
            userRoleAPIClient.deleteUser(id);
            return ResponseEntity.ok().body(u);
        } catch (ApiException e) {
            e.printStackTrace();
            if(e.getResponseBody() == null)
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return ResponseEntity.status(e.getCode()).body(e.getResponseBody());
        }
    }

    public ResponseEntity getUserById(@ApiParam(value = "",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                User u = userRoleAPIClient.getUserById(id);
                return ResponseEntity.ok().body(u);
            } catch (ApiException e) {
                e.printStackTrace();
                if(e.getResponseBody() == null)
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
                return ResponseEntity.status(e.getCode()).body(e.getResponseBody());
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return ResponseEntity.badRequest().body(error);
    }

    public ResponseEntity getUsers(@ApiParam(value = "Parameters of the user"  )  @Valid @RequestBody UserQuery query) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<User> us = userRoleAPIClient.getUsers(query);
                return ResponseEntity.ok().body(us);
            } catch (ApiException e) {
                e.printStackTrace();
                if(e.getResponseBody() == null)
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
                return ResponseEntity.status(e.getCode()).body(e.getResponseBody());
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return ResponseEntity.badRequest().body(error);
    }

}
