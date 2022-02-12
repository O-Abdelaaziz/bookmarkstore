package com.bookmarkstoreserver.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.bookmarkstoreserver.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Created 11/02/2022 - 09:47
 * @Package com.bookmarkstoreserver.entity
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
@ApiModel(description = "Category model information")
public class Category extends BaseEntity {
    //region Simple Properties
    @NotBlank(message = "name cannot be empty")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "description", nullable = true)
    private String description;

    @NotBlank
    @Column(name = "color", nullable = false)
    private String color;

    @NotBlank
    @Column(name = "icon", nullable = false)
    private String icon;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name="parent_id")
    @JsonIgnore
    private Category parentId;
    //endregion

    //region Complex Properties
    @OneToMany(mappedBy="parentId")
    private List<Category> subCategories=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true, targetEntity = Link.class)
    @OrderColumn(name = "id")
    private List<Link> linkList=new ArrayList<>();
    //endregion
}
