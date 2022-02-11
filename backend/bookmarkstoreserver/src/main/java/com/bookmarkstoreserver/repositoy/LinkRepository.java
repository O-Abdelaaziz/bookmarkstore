package com.bookmarkstoreserver.repositoy;

import com.bookmarkstoreserver.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created 11/02/2022 - 11:25
 * @Package com.bookmarkstoreserver.repositoy
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByUid(String uid);
}
