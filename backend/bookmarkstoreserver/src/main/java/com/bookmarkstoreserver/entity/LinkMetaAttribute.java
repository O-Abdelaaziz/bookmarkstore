package com.bookmarkstoreserver.entity;

import javax.persistence.*;

import com.bookmarkstoreserver.entity.common.BaseEntity;
import lombok.*;

/**
 * @Created 11/02/2022 - 10:55
 * @Package com.bookmarkstoreserver.entity
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "link_meta_attributes")
public class LinkMetaAttribute extends BaseEntity {
    //region Simple Properties
    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    private String type;

    @Column(name = "author")
    private String author;

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "description")
    private String description;

    @Column(name = "fav_icon")
    private String favIcon;

    @Column(name = "image")
    private String image;
    //endregion

    //region Complex Properties
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "link_id", referencedColumnName = "id")
    private Link link;
    //endregion
}
