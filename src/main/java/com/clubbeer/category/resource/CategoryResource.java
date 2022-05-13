package com.clubbeer.category.resource;

import com.clubbeer.category.domain.CategoryStatus;
import com.clubbeer.category.entity.Category;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResource {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty("name")
    @NotBlank
    @Size(max = 255)
    private String name;

    @JsonProperty("code")
    @NotBlank
    @Size(max = 255)
    private String code;

    @JsonProperty("status")
    @NotNull
    private CategoryStatus status;

    public CategoryResource(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.code = category.getCode();
        this.status = category.getStatus();
    }

    public Category toEntity() {
        return Category.builder()
                .id(this.id)
                .code(this.code)
                .name(this.name)
                .status(this.status)
                .build();
    }
}
