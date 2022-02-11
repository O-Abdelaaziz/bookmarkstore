package com.bookmarkstoreserver.repositoy;

import com.bookmarkstoreserver.entity.LinkMetaAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created 11/02/2022 - 11:28
 * @Package com.bookmarkstoreserver.repositoy
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Repository
public interface LinkMetaAttributeRepository extends JpaRepository<LinkMetaAttribute, Long> {
    LinkMetaAttribute findByUid(String uid);
}
