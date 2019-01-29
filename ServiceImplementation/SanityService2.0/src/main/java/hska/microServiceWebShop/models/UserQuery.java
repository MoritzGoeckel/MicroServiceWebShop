package hska.microServiceWebShop.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

/**
 * UserQuery
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-03T22:24:38.514Z")

public class UserQuery   {
  @JsonProperty("username")
  private String username = null;

  @JsonProperty("text")
  private String text = null;

  @JsonProperty("role")
  private Long role = null;

  public UserQuery username(String username) {
    this.username = username;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public UserQuery text(String text) {
    this.text = text;
    return this;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public UserQuery role(Long role) {
    this.role = role;
    return this;
  }

  public Long getRole() {
    return role;
  }

  public void setRole(Long role) {
    this.role = role;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserQuery userQuery = (UserQuery) o;
    return Objects.equals(this.username, userQuery.username) &&
        Objects.equals(this.text, userQuery.text) &&
        Objects.equals(this.role, userQuery.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, text, role);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserQuery {\n");
    
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

