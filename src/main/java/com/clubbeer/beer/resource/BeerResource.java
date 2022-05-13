package com.clubbeer.beer.resource;

import com.clubbeer.beer.entity.Beer;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeerResource {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("description")
    private String description;

    @JsonProperty("price")
    private Long price;

    @JsonProperty("category_id")
    private Long categoryId;

    @JsonProperty("manufacturer_id")
    private Long manufacturerId;

    @JsonProperty("country_id")
    private Long countryId;

    public BeerResource(Beer beer) {
        this.id = beer.getId();
        this.name = beer.getName();
        this.code = beer.getCode();
        this.description = beer.getDescription();
        this.price = beer.getPrice();
        this.categoryId = beer.getCategoryId();
        this.manufacturerId = beer.getManufacturerId();
        this.countryId = beer.getCountryId();
    }

    public Beer toEntity() {
        return Beer.builder()
                .id(this.id)
                .name(this.name)
                .code(this.code)
                .description(this.description)
                .price(this.price)
                .categoryId(this.categoryId)
                .manufacturerId(this.manufacturerId)
                .countryId(this.countryId)
                .build();
    }
}
