package com.bookmarkstoreserver.entity;

import javax.persistence.*;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @Created 11/02/2022 - 09:17
 * @Package com.bookmarkstoreserver.entity
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "root_url")
    private String rootUrl;

    @Column(name = "sub_url")
    private String subUrl;

    @Column(name = "view_count")
    private Integer viewCount;

    @Column(name = "metadata")
    private String metadata;

    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "is_favorite")
    private Boolean isFavorite;

    @Column(name = "is_archived")
    private Boolean isArchived;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @Column(name = "archived_date")
    private LocalDateTime archivedDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
}
