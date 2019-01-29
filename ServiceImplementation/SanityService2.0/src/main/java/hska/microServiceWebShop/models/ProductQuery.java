package hska.microServiceWebShop.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;
/**
 * ProductQuery
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-01-03T22:24:38.514Z")

public class ProductQuery   {
  @JsonProperty("text")
  private String text = null;

  @JsonProperty("priceMin")
  private Double priceMin = null;

  @JsonProperty("priceMax")
  private Double priceMax = null;

  @JsonProperty("category")
  private Long category = null;

  public ProductQuery text(String text) {
    this.text = text;
    return this;
  }


  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public ProductQuery priceMin(Double priceMin) {
    this.priceMin = priceMin;
    return this;
  }

  public Double getPriceMin() {
    return priceMin;
  }

  public void setPriceMin(Double priceMin) {
    this.priceMin = priceMin;
  }

  public ProductQuery priceMax(Double priceMax) {
    this.priceMax = priceMax;
    return this;
  }

  public Double getPriceMax() {
    return priceMax;
  }

  public void setPriceMax(Double priceMax) {
    this.priceMax = priceMax;
  }

  public ProductQuery category(Long category) {
    this.category = category;
    return this;
  }

  public Long getCategory() {
    return category;
  }

  public void setCategory(Long category) {
    this.category = category;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductQuery productQuery = (ProductQuery) o;
    return Objects.equals(this.text, productQuery.text) &&
        Objects.equals(this.priceMin, productQuery.priceMin) &&
        Objects.equals(this.priceMax, productQuery.priceMax) &&
        Objects.equals(this.category, productQuery.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(text, priceMin, priceMax, category);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductQuery {\n");
    
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
    sb.append("    priceMin: ").append(toIndentedString(priceMin)).append("\n");
    sb.append("    priceMax: ").append(toIndentedString(priceMax)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
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

