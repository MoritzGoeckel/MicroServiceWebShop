package hska.microServiceWebShop.service.SanityService;

import hska.microServiceWebShop.ApiException;
import hska.microServiceWebShop.models.Error;
import hska.microServiceWebShop.models.Role;
import hska.microServiceWebShop.models.RoleQuery;
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
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-03T22:24:38.514Z")

@Controller
public class RolesApiController implements RolesApi {

    private static final Logger log = LoggerFactory.getLogger(RolesApiController.class);

    hska.microServiceWebShop.api.UserRoleApi userRoleAPIClient = new hska.microServiceWebShop.api.UserRoleApi();
    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public RolesApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity createRole(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Role role) {
        String accept = request.getHeader("Accept");
        try {
            userRoleAPIClient.createRole(role);
            return ResponseEntity.ok().body(role);
        } catch (ApiException e) {
            e.printStackTrace();
            if(e.getResponseBody() == null)
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return ResponseEntity.status(e.getCode()).body(e.getResponseBody());
        }
    }

    public ResponseEntity deleteRole(@ApiParam(value = "",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        try {
            Role r = userRoleAPIClient.getRoleById(id);
            userRoleAPIClient.deleteRole(id);
            return ResponseEntity.ok().body(r);
        } catch (ApiException e) {
            e.printStackTrace();
            if(e.getResponseBody() == null)
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            return ResponseEntity.status(e.getCode()).body(e.getResponseBody());
        }
    }

    public ResponseEntity getRoleById(@ApiParam(value = "",required=true) @PathVariable("id") Long id) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Role r = userRoleAPIClient.getRoleById(id);
                return ResponseEntity.ok().body(r);
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

    public ResponseEntity getRoles(@ApiParam(value = "Parameters of the role"  )  @Valid @RequestBody RoleQuery query) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Role> rs = userRoleAPIClient.getRoles(query);
                return ResponseEntity.ok().body(rs);
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
