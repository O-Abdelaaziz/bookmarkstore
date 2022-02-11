package com.bookmarkstoreserver.repositoy;

import com.bookmarkstoreserver.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Created 11/02/2022 - 11:27
 * @Package com.bookmarkstoreserver.repositoy
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByUid(String uid);
    Category findByNameContainingIgnoreCase(String name);
}
