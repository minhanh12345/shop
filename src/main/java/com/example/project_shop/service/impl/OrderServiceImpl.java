package com.example.project_shop.service.impl;

import com.example.project_shop.entity.OrderDetailEntity;
import com.example.project_shop.entity.OrderEntity;
import com.example.project_shop.model.OrderViewAdmin;
import com.example.project_shop.model.OrderViewDetail;
import com.example.project_shop.repository.OrderDetailRepo;
import com.example.project_shop.repository.OrderRepo;
import com.example.project_shop.repository.VegRepo;
import com.example.project_shop.service.OrderService;
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
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    OrderDetailRepo orderDetailRepo;
    @Autowired
    VegRepo vegRepo;

    @Override
    public List<OrderViewAdmin> getAllWithPagingAndSorting(PagingAndSortingModel model) {
        Page<OrderEntity> page;
        Specification<OrderEntity> joinSpecification = (vegJoin, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterIdOrder())) {
                predicateList.add(builder.equal(vegJoin.get("id"), model.getSearchCriteria().getFilterIdOrder()));
            }
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterStatusOrder())) {
                predicateList.add(builder.equal(vegJoin.get("transStatus"), model.getSearchCriteria().getFilterStatusOrder()));
            }
            if (StringUtils.isNotBlank(model.getSearchCriteria().getFilterTypePayOrder())) {
                predicateList.add(builder.equal(vegJoin.get("typePay"), model.getSearchCriteria().getFilterTypePayOrder()));
            }

            query.distinct(true);
            return builder.and(predicateList.toArray(new Predicate[0]));
        };
        page = orderRepo.findAll(joinSpecification, CommonUtil.buildPageable(model.getPageIndex(),
                model.getPageSize(), model.getSortColumn(), model.getSortDirection()));
        List<OrderViewAdmin> orderViewAdmins = convertToViewAdmin(page.getContent());

        return orderViewAdmins;
    }

    @Override
    public List<OrderViewDetail> getAllODByOrder(Long idOder) {
        List<OrderDetailEntity> list = orderDetailRepo.getAllByOrder(idOder);
        List<OrderViewDetail> orderViewDetails=convertToODV(list);
        return orderViewDetails;
    }

    private List<OrderViewAdmin> convertToViewAdmin(List<OrderEntity> list) {
        List<OrderViewAdmin> orderViewAdmins = new ArrayList<>();
        for (OrderEntity o : list) {
            OrderViewAdmin orderViewAdmin = new OrderViewAdmin();
            List<OrderDetailEntity> odLst = orderDetailRepo.getAllByOrder(o.getId());
            float totalPrice = 0;
            for (OrderDetailEntity ode : odLst) {
                totalPrice += ode.getVegetable().getPrice();
            }
            orderViewAdmin.setTotalPrice(totalPrice);
            orderViewAdmin.setIdOrder(o.getId());
            orderViewAdmin.setOrderDate(o.getOrderDate());
            orderViewAdmin.setShipDate(o.getShipDate());
            orderViewAdmin.setStatus(o.getTransStatus());
            orderViewAdmin.setNameCustomer(o.getCustomer().getFullName());
            orderViewAdmin.setTypePay(o.getTypePay());
            orderViewAdmins.add(orderViewAdmin);
        }
        return orderViewAdmins;
    }

    private List<OrderViewDetail> convertToODV(List<OrderDetailEntity> list){
        List<OrderViewDetail> orderViewDetailList=new ArrayList<>();
        for (OrderDetailEntity ode:list) {
         OrderViewDetail ovd=new OrderViewDetail();
         ovd.setAmount(ode.getAmount());
         ovd.setVegName(ode.getVegetable().getName());
         ovd.setPrice(ode.getVegetable().getPrice());
         orderViewDetailList.add(ovd);
        }
        return orderViewDetailList;
    }
}
