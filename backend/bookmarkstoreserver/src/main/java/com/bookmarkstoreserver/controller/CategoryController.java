package com.bookmarkstoreserver.controller;

import com.bookmarkstoreserver.controller.noun.APINoun;
import com.bookmarkstoreserver.entity.Category;
import com.bookmarkstoreserver.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Created 11/02/2022 - 12:12
 * @Package com.bookmarkstoreserver.controller
 * @Project bookmarkstoreserver
 * @User LegendDZ
 * @Author Abdelaaziz Ouakala
 **/
@RestController
@RequestMapping(APINoun.CATEGORIES)
public class CategoryController {

    private ICategoryService iCategoryService;

    @Autowired
    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(iCategoryService.findAll());
    }

    @GetMapping(APINoun.CATEGORY_PATH_UID)
    private ResponseEntity<Category> findByUid(@PathVariable(name = APINoun.CATEGORY_VARIABLE_UID) String uid) {
        return ResponseEntity.ok(iCategoryService.findByUid(uid));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Category> save(@Valid @RequestBody Category category) {
        Category savedCategory = iCategoryService.save(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(APINoun.CATEGORY_PATH_UID)
                .buildAndExpand(savedCategory.getUid())
                .toUri();
        return ResponseEntity.created(location).body(savedCategory);
    }

    @PutMapping(path = APINoun.CATEGORY_PATH_UID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Category> save(@PathVariable(name = APINoun.CATEGORY_VARIABLE_UID) String uid, @Valid @RequestBody Category category) {
        Category updatedCategory = iCategoryService.update(uid, category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(APINoun.CATEGORY_PATH_UID)
                .buildAndExpand(updatedCategory.getUid())
                .toUri();
        return ResponseEntity.created(location).body(updatedCategory);
    }

    @DeleteMapping(APINoun.CATEGORY_PATH_UID)
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable(name = APINoun.CATEGORY_VARIABLE_UID) String uid) {
        iCategoryService.delete(uid);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
