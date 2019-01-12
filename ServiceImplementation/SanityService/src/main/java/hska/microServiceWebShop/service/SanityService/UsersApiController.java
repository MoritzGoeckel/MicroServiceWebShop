package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import hska.microServiceWebShop.models.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersApiController implements UsersApi {

    hska.microServiceWebShop.api.UserRoleApi userRoleAPIClient = new hska.microServiceWebShop.api.UserRoleApi();

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity createUser(@RequestBody User user) {
        String accept = request.getHeader("Accept");
        try {
            userRoleAPIClient.createUser(user);
            return ResponseEntity.ok().body(user);
        } catch (ApiException e) {
            e.printStackTrace();
            Error error = new Error();
            error.setDescription(e.getMessage());
            return ResponseEntity.status(e.getCode()).body(error);
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
            return ResponseEntity.status(e.getCode()).body(error);
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
                return ResponseEntity.status(e.getCode()).body(error);
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return ResponseEntity.badRequest().body(error);
    }

    public ResponseEntity getUsers(@RequestHeader(value="Text",defaultValue = "") String text,
                                   @RequestHeader(value="Role",defaultValue = "") Long level,
                                   @RequestHeader(value="Username",defaultValue = "") String name) {
        UserQuery query = new UserQuery();
        query.setText(text);
        query.setUsername(name);
        query.setRole(level);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<User> us = userRoleAPIClient.getUsers(query);
                return ResponseEntity.ok().body(us);
            } catch (ApiException e) {
                e.printStackTrace();
                Error error = new Error();
                error.setDescription(e.getMessage());
                return ResponseEntity.status(e.getCode()).body(error);
            }
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return ResponseEntity.badRequest().body(error);
    }

}
