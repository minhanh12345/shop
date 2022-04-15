package com.example.project_shop.service.impl;

import com.example.project_shop.entity.BlogEntity;
import com.example.project_shop.entity.UserEntity;
import com.example.project_shop.repository.BlogRepo;
import com.example.project_shop.service.BlogService;
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
public class BlogServiceImpl implements BlogService {
    @Autowired
    BlogRepo blogRepo;

    @Override
    public List<BlogEntity> getAllWithPagingAndSorting(PagingAndSortingModel model) {
        Page<BlogEntity> page;
        Specification<BlogEntity> joinSpecification = (vegJoin, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterCategoryBlog())) {
                predicateList.add(builder.equal(vegJoin.get("category"), model.getSearchCriteria().getFilterCategoryBlog()));
            }

            query.distinct(true);
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
        page = blogRepo.findAll(joinSpecification, CommonUtil.buildPageable(model.getPageIndex(),
                model.getPageSize(), model.getSortColumn(), model.getSortDirection()));

        return page.getContent();
    }

    @Override
    public BlogEntity save(BlogEntity blog) {
        return blogRepo.save(blog);
    }

    @Override
    public void delete(Long idBlog) {
        blogRepo.delete(blogRepo.getById(idBlog));
    }
}
