package com.bookmarkstoreserver.controller;

import com.bookmarkstoreserver.controller.noun.APINoun;
import com.bookmarkstoreserver.entity.Category;
import com.bookmarkstoreserver.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "Bookmark Store", description = "Operations pertaining to category in bookmark Store")
@RestController
@RequestMapping(APINoun.CATEGORIES)
public class CategoryController {

    private ICategoryService iCategoryService;

    @Autowired
    public CategoryController(ICategoryService iCategoryService) {
        this.iCategoryService = iCategoryService;
    }

    @ApiOperation(value = "View a list of available categories", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(iCategoryService.findAll());
    }

    @ApiOperation(value = "Search a category with an UID", response = Category.class)
    @GetMapping(path = APINoun.CATEGORY_PATH_UID, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Category> findByUid(@PathVariable(name = APINoun.CATEGORY_VARIABLE_UID) String uid) {
        return ResponseEntity.ok(iCategoryService.findByUid(uid));
    }

    @ApiOperation(value = "Save a category object", response = Category.class)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Category> save(@Valid @RequestBody Category category) {
        Category savedCategory = iCategoryService.save(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(APINoun.CATEGORY_PATH_UID)
                .buildAndExpand(savedCategory.getUid())
                .toUri();
        return ResponseEntity.created(location).body(savedCategory);
    }

    @ApiOperation(value = "Update a category object", response = Category.class)
    @PutMapping(path = APINoun.CATEGORY_PATH_UID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Category> update(@PathVariable(name = APINoun.CATEGORY_VARIABLE_UID) String uid, @Valid @RequestBody Category category) {
        Category updatedCategory = iCategoryService.update(uid, category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(APINoun.CATEGORY_PATH_UID)
                .buildAndExpand(updatedCategory.getUid())
                .toUri();
        return ResponseEntity.created(location).body(updatedCategory);
    }

    @ApiOperation(value = "Delete a category object", response = Category.class)
    @DeleteMapping(APINoun.CATEGORY_PATH_UID)
    public ResponseEntity<Map<String, Boolean>> delete(@PathVariable(name = APINoun.CATEGORY_VARIABLE_UID) String uid) {
        iCategoryService.delete(uid);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
