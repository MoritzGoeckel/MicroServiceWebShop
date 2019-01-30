package hska.iwi.eShopMaster.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-03T22:24:38.514Z")

public class Role   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("typ")
  private String typ = null;

  @JsonProperty("level")
  private Integer level = null;

  public Role id(Long id) {
    this.id = id;
    return this;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Role typ(String typ) {
    this.typ = typ;
    return this;
  }

  public String getTyp() {
    return typ;
  }

  public void setTyp(String typ) {
    this.typ = typ;
  }

  public Role level(Integer level) {
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
    Role role = (Role) o;
    return Objects.equals(this.id, role.id) &&
        Objects.equals(this.typ, role.typ) &&
        Objects.equals(this.level, role.level);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, typ, level);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Role {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    typ: ").append(toIndentedString(typ)).append("\n");
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

