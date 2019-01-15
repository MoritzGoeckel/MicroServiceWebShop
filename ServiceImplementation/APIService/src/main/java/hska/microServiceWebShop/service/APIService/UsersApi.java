/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1-SNAPSHOT).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package hska.microServiceWebShop.service.APIService;

import hska.microServiceWebShop.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface UsersApi {
    @RequestMapping(value = "/users",
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity createUser(@RequestBody User user);

    @RequestMapping(value = "/users/{id}",
            method = RequestMethod.DELETE)
    ResponseEntity deleteUser(@PathVariable("id") Long id);

    @RequestMapping(value = "/users/{id}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity getUserById(@PathVariable("id") Long id);

    @RequestMapping(value = "/users",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity getUsers(@RequestHeader(value="Text",defaultValue = "") String text,
                            @RequestHeader(value="Role",defaultValue = "") Long roleId,
                            @RequestHeader(value="Username",defaultValue = "") String name);

}

