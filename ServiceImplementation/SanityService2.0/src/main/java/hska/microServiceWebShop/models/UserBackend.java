package hska.microServiceWebShop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * UserBackend
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-04T00:32:34.965Z")

public class UserBackend {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("roleID")
  private Long roleID = null;

  public UserBackend id(Long id) {
    this.id = id;
    return this;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserBackend username(String username) {
    this.username = username;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserBackend firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public UserBackend lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public UserBackend password(String password) {
    this.password = password;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserBackend roleID(Long roleID) {
    this.roleID = roleID;
    return this;
  }

  public Long getRoleID() {
    return roleID;
  }

  public void setRoleID(Long roleID) {
    this.roleID = roleID;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserBackend userBackend = (UserBackend) o;
    return Objects.equals(this.id, userBackend.id) &&
        Objects.equals(this.username, userBackend.username) &&
        Objects.equals(this.firstName, userBackend.firstName) &&
        Objects.equals(this.lastName, userBackend.lastName) &&
        Objects.equals(this.password, userBackend.password) &&
        Objects.equals(this.roleID, userBackend.roleID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, firstName, lastName, password, roleID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserBackend {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    roleID: ").append(toIndentedString(roleID)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

