package com.clubbeer.beer.controller;

import com.clubbeer.beer.resource.BeerResource;
import com.clubbeer.beer.service.BeerService;
import com.clubbeer.common.response.ResultResponse;
import com.clubbeer.common.utils.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class BeerController {
    private final Logger log = LoggerFactory.getLogger(BeerController.class);
    private static final String ENTITY_NAME = "beer";
    private static final String BASE_URL_BEERS = "/beers";

    private final BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping(BASE_URL_BEERS)
    public ResponseEntity<ResultResponse<BeerResource>> findAll(
            @RequestParam int page,
            @RequestParam int size) {
        log.debug("REST request to get all Beers");
        return ResponseEntity.ok(beerService.getBeerAll(page, size));
    }

    @GetMapping(BASE_URL_BEERS + "/{id}")
    public ResponseEntity<BeerResource> findById(@PathVariable Long id) throws Exception {
        log.debug("REST request to get Beers by id: ", id);
        return ResponseEntity.ok(beerService.getBeerById(id));
    }

    @PostMapping(BASE_URL_BEERS)
    public ResponseEntity<BeerResource> createCategory(@Valid @RequestBody BeerResource beerResource) throws URISyntaxException {
        log.debug("REST request to save Beer : {}", beerResource);
        BeerResource result = beerService.createBeer(beerResource);
        return ResponseEntity
                .created(new URI(String.format("%s/%s", BASE_URL_BEERS, result.getId())))
                .headers(HeaderUtil.createEntityCreationAlert( true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping(BASE_URL_BEERS + "/{id}")
    public ResponseEntity<BeerResource> updateCategory(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody BeerResource beerResource){
        log.debug("REST request to update Beer : {}, {}", id, beerResource);
        BeerResource result = beerService.updateBeer(id, beerResource);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(true, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @DeleteMapping(BASE_URL_BEERS + "/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable Long id) {
        log.debug("REST request to delete Beer");
        beerService.deleteBeerById(id);
        return ResponseEntity.noContent().build();
    }
}
