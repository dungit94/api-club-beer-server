package com.clubbeer.beer.service;

import com.clubbeer.beer.entity.Beer;
import com.clubbeer.beer.repository.IBeerRepository;
import com.clubbeer.beer.resource.BeerResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BeerService {
    private final IBeerRepository iBeerRepository;

    @Autowired
    public BeerService(IBeerRepository iBeerRepository) {
        this.iBeerRepository = iBeerRepository;
    }

    public BeerResource getBeerById(Long id) throws Exception {
        Beer beer = iBeerRepository.findById(id)
                .orElseThrow(() -> new Exception());
        return new BeerResource(beer);
    }

    public List<BeerResource> getBeerAll() {
        List<Beer> beers = iBeerRepository.findAll();
        return convertEntityToResource(beers);
    }

    public BeerResource createBeer(BeerResource beerResource) {
        Beer beer = iBeerRepository.save(beerResource.toEntity());
        return new BeerResource(beer);
    }

    public BeerResource updateBeer(Long id, BeerResource beerResource) {
        beerResource.setId(id);
        Beer beer = iBeerRepository.save(beerResource.toEntity());
        return new BeerResource(beer);
    }

    public void deleteBeerById(Long id) {
        iBeerRepository.deleteById(id);
    }

    public List<BeerResource> convertEntityToResource(List<Beer> beers){
        return beers.stream()
                .filter(Objects::nonNull)
                .map(BeerResource::new)
                .collect(Collectors.toList());
    }
}
