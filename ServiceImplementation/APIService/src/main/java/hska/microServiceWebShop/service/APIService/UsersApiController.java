package hska.microServiceWebShop.service.APIService;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.User;
import hska.microServiceWebShop.models.UserBackend;
import hska.microServiceWebShop.models.UserQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);
    
    @Autowired
    hska.microServiceWebShop.api.UserRoleApi userRoleAPIClient;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity createUser(@RequestBody UserBackend userBackend) {
        String accept = request.getHeader("Accept");
        try {
            User u = userRoleAPIClient.createUser(userBackend);
            return ResponseEntity.ok().body(u);
        } catch (ApiException e) {
            e.printStackTrace();
            Error error = new Error();
            error.setDescription(e.getMessage());
            return new ResponseEntity<Error>(error,HttpStatus.valueOf(e.getCode()));
        }
    }

    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        try {
            User u = userRoleAPIClient.getUserById(id);
            userRoleAPIClient.deleteUser(id);
            return ResponseEntity.ok().body(u);
        } catch (ApiException e) {
            e.printStackTrace();
            Error error = new Error();
            error.setDescription(e.getMessage());
            return new ResponseEntity<Error>(error,HttpStatus.valueOf(e.getCode()));
        }
    }

    public ResponseEntity getUserById(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                User u = userRoleAPIClient.getUserById(id);
                return ResponseEntity.ok().body(u);
            } catch (ApiException e) {
                e.printStackTrace();
                Error error = new Error();
                error.setDescription(e.getMessage());
                return new ResponseEntity<Error>(error,HttpStatus.valueOf(e.getCode()));
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity getUsers(@RequestHeader(value="Text",defaultValue = "") String text,
                                   @RequestHeader(value="Role",defaultValue = "") Long roleId,
                                   @RequestHeader(value="Username",defaultValue = "") String name) {
        UserQuery query = new UserQuery();
        query.setText(text);
        query.setUsername(name);
        query.setRole(roleId);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<User> us = userRoleAPIClient.getUsers(query);
                return ResponseEntity.ok().body(us);
            } catch (ApiException e) {
                e.printStackTrace();
                Error error = new Error();
                error.setDescription(e.getMessage());
                return new ResponseEntity<Error>(error,HttpStatus.valueOf(e.getCode()));
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
    }

}
