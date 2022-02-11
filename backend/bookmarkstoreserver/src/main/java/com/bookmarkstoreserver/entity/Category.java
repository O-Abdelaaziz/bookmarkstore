package com.bookmarkstoreserver.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "icon", nullable = false)
    private String icon;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @ManyToOne
    @JoinColumn(name="parent_id")
    @JsonIgnore
    private Category parentId;

    @OneToMany(mappedBy="parentId")
    private List<Category> subCategories=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category", orphanRemoval = true, targetEntity = Link.class)
    @OrderColumn(name = "id")
    private List<Link> linkList=new ArrayList<>();
}
