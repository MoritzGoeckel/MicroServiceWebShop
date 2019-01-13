package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.Clients.UserRoleApi;
import hska.microServiceWebShop.models.*;
import hska.microServiceWebShop.models.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    private UserRoleApi userRoleAPIClient;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity createUser(@RequestBody User user) {
        String accept = request.getHeader("Accept");
        userRoleAPIClient.createUser(user);
        return ResponseEntity.ok().body(user);
    }

    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        User u = userRoleAPIClient.getUser(id);
        userRoleAPIClient.deleteUser(id);
        return ResponseEntity.ok().body(u);
    }

    public ResponseEntity getUserById(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            User u = userRoleAPIClient.getUser(id);
            return ResponseEntity.ok().body(u);
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
            List<User> us = userRoleAPIClient.getUsers(query.getUsername(),query.getText(),query.getRole());
            return ResponseEntity.ok().body(us);
        }

        Error error = new Error();
        error.description("wrong acept datatye");
        return ResponseEntity.badRequest().body(error);
    }

}
