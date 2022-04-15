package com.example.project_shop.service.impl;

import com.example.project_shop.entity.CommentEntity;
import com.example.project_shop.repository.CommentRepo;
import com.example.project_shop.service.CommentService;
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
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepo commentRepo;

    @Override
    public List<CommentEntity> getAllWithPagingAndSorting(PagingAndSortingModel model) {
        Page<CommentEntity> page;
        Specification<CommentEntity> joinSpecification = (vegJoin, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (model.getSearchCriteria().getFilterIdBlog() != null) {
                predicateList.add(builder.equal(vegJoin.get("blog").get("id"), model.getSearchCriteria().getFilterIdBlog()));
            }

            query.distinct(true);
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
        page = commentRepo.findAll(joinSpecification, CommonUtil.buildPageable(model.getPageIndex(),
                model.getPageSize(), model.getSortColumn(), model.getSortDirection()));

        return page.getContent();
    }

    public List<CommentEntity> getRepComment(Long idCmt) {
        List<CommentEntity> lst = new ArrayList<>();
        Optional<List<CommentEntity>> list = commentRepo.getAllByParentComment(idCmt);
        if (list.isPresent()) {
            lst = list.get();
        }
        return lst;
    }

    @Override
    public CommentEntity save(CommentEntity blog) {
        return commentRepo.save(blog);
    }

    @Override
    public void delete(Long idCmt) {
        commentRepo.delete(commentRepo.getById(idCmt));
    }
}
