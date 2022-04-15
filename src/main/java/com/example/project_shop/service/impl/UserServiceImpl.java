package com.example.project_shop.service.impl;

import com.example.project_shop.entity.UserEntity;
import com.example.project_shop.entity.VegetableEntity;
import com.example.project_shop.repository.UserRepo;
import com.example.project_shop.service.UserService;
import com.example.project_shop.util.CommonUtil;
import com.example.project_shop.util.PagingAndSortingModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Override
    public List<UserEntity> getAllWithPagingAndSorting(PagingAndSortingModel model) {
        Page<UserEntity> page;
        Specification<UserEntity> joinSpecification = (vegJoin, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterRoleUser())) {
                predicateList.add(builder.equal(vegJoin.get("role"), model.getSearchCriteria().getFilterRoleUser()));
            }
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterPhoneUser())) {
                predicateList.add(builder.like(vegJoin.get("phone"), "%" + model.getSearchCriteria().getFilterPhoneUser() + "%"));
            }
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterNameUser())) {
                predicateList.add(builder.like(vegJoin.get("fullName"), "%" + model.getSearchCriteria().getFilterNameUser() + "%"));
            }
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterUsername())) {
                predicateList.add(builder.like(vegJoin.get("username"), "%" + model.getSearchCriteria().getFilterUsername() + "%"));
            }
            query.distinct(true);
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
        page = userRepo.findAll(joinSpecification, CommonUtil.buildPageable(model.getPageIndex(),
                model.getPageSize(), model.getSortColumn(), model.getSortDirection()));

        return page.getContent();

    }

    @Override
    public UserEntity save(UserEntity user) {
        UserEntity userEntity = userRepo.save(user);
        return userEntity;
    }

    @Override
    public void delete(Long idUser) {
        userRepo.delete(userRepo.getById(idUser));
    }
}
