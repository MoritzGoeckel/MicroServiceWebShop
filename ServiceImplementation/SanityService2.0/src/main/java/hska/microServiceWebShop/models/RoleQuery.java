package hska.microServiceWebShop.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

/**
 * RoleQuery
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-03T22:24:38.514Z")

public class RoleQuery   {
  @JsonProperty("text")
  private String text = null;

  @JsonProperty("level")
  private Integer level = null;

  public RoleQuery text(String text) {
    this.text = text;
    return this;
  }


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public RoleQuery level(Integer level) {
    this.level = level;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RoleQuery roleQuery = (RoleQuery) o;
    return Objects.equals(this.text, roleQuery.text) &&
        Objects.equals(this.level, roleQuery.level);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, level);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RoleQuery {\n");
    
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    level: ").append(toIndentedString(level)).append("\n");
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

