package com.example.project_shop.service;

import com.example.project_shop.entity.VegetableEntity;
import com.example.project_shop.util.PagingAndSortingModel;

import java.util.List;

public interface VegService {
    List<VegetableEntity> getAllWithPagingAndSorting(PagingAndSortingModel model);
    VegetableEntity save(VegetableEntity vegetable);
    void delete(Long idVeg);
}
