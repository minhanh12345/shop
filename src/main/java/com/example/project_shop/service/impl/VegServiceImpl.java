package com.example.project_shop.service.impl;

import com.example.project_shop.entity.UserEntity;
import com.example.project_shop.entity.VegetableEntity;
import com.example.project_shop.repository.VegRepo;
import com.example.project_shop.service.VegService;
import com.example.project_shop.util.CommonUtil;
import com.example.project_shop.util.PagingAndSortingModel;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class VegServiceImpl implements VegService {
    @Autowired
    VegRepo vegRepo;


    @Override
    public List<VegetableEntity> getAllWithPagingAndSorting(PagingAndSortingModel model) {
        Page<VegetableEntity> page;
        Specification<VegetableEntity> joinSpecification = (vegJoin, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterVegType())) {
                predicateList.add(builder.equal(vegJoin.get("typeVeg"), model.getSearchCriteria().getFilterVegType()));
            }
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterNameVeg())) {
                predicateList.add(builder.like(vegJoin.get("name"), "%" + model.getSearchCriteria().getFilterNameVeg() + "%"));
            }
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterVegSupplier())) {
                predicateList.add(builder.equal(vegJoin.get("supplier").get("name"), model.getSearchCriteria().getFilterVegSupplier()));
            }
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterVegStatus())) {
                predicateList.add(builder.equal(vegJoin.get("available"), model.getSearchCriteria().getFilterVegStatus()));
            }
            query.distinct(true);
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
        page = vegRepo.findAll(joinSpecification, CommonUtil.buildPageable(model.getPageIndex(),
                model.getPageSize(), model.getSortColumn(), model.getSortDirection()));

        return page.getContent();
    }
}
