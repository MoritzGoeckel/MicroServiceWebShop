package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.Clients.UserRoleApi;
import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.Role;
import hska.microServiceWebShop.models.RoleQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class RolesApiController implements RolesApi {

    @Autowired
    private UserRoleApi userRoleAPIClient;
    //hska.microServiceWebShop.api.UserRoleApi userRoleAPIClient = new hska.microServiceWebShop.api.UserRoleApi();

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RolesApiController(HttpServletRequest request) {
        this.request = request;
    }

    public ResponseEntity createRole(@RequestBody Role role) {
        String accept = request.getHeader("Accept");
        try {
            userRoleAPIClient.createRole(role);
            return ResponseEntity.ok().body(role);
        } catch (ApiException e) {
            e.printStackTrace();
            Error error = new Error();
            error.setDescription(e.getMessage());
            return ResponseEntity.status(e.getCode()).body(error);
        }
    }

    public ResponseEntity deleteRole(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        try {
            Role r = userRoleAPIClient.getRole(id);
            userRoleAPIClient.deleteRole(id);
            return ResponseEntity.ok().body(r);
        } catch (ApiException e) {
            e.printStackTrace();
            Error error = new Error();
            error.setDescription(e.getMessage());
            return ResponseEntity.status(e.getCode()).body(error);
        }
    }

    public ResponseEntity getRoleById(@PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Role r = userRoleAPIClient.getRole(id);
                return ResponseEntity.ok().body(r);
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

    public ResponseEntity getRoles(@RequestHeader(value="Text",defaultValue = "") String text,
                                   @RequestHeader(value="Level",defaultValue = "") int level) {
        RoleQuery query = new RoleQuery();
        query.setText(text);
        query.setLevel(level);
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Role> rs = userRoleAPIClient.getRoles(query.getText(),query.getLevel());
                return ResponseEntity.ok().body(rs);
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
