package com.example.project_shop.util;

import lombok.Data;

@Data
public class SearchCriteria {
    private Long id;
    private String filterVegType;
    private String filterNameVeg;
    private String filterVegStatus;
    private String filterVegDiscount;
    private String filterVegSupplier;

    private String filterRoleUser;
    private String filterPhoneUser;
    private String filterNameUser;
    private String filterUsername;

    private String filterIdOrder;
    private String filterStatusOrder;
    private String filterTypePayOrder;

    private String filterCategoryBlog;

    private Long filterIdBlog;

    public SearchCriteria(Long filterIdBlog) {
        this.filterIdBlog = filterIdBlog;
    }

    public SearchCriteria() {
    }

    public SearchCriteria(String filterCategoryBlog) {
        this.filterCategoryBlog = filterCategoryBlog;
    }

    public SearchCriteria(String filterVegType, String filterNameVeg, String filterVegStatus, String filterVegDiscount, String filterVegSupplier) {
        this.filterVegType = filterVegType;
        this.filterNameVeg = filterNameVeg;
        this.filterVegStatus = filterVegStatus;
        this.filterVegDiscount = filterVegDiscount;
        this.filterVegSupplier = filterVegSupplier;
    }

    public boolean isEmptyFilters() {
        return CommonUtil.stringIsNullOrEmpty(filterNameVeg) &&
                CommonUtil.stringIsNullOrEmpty(filterVegType) &&
                CommonUtil.stringIsNullOrEmpty(filterVegStatus) &&
                CommonUtil.stringIsNullOrEmpty(filterVegDiscount);
    }

    public SearchCriteria(String filterRoleUser, String filterPhoneUser, String filterNameUser, String filterUsername) {
        this.filterRoleUser = filterRoleUser;
        this.filterPhoneUser = filterPhoneUser;
        this.filterNameUser = filterNameUser;
        this.filterUsername = filterUsername;
    }

    public SearchCriteria(String filterIdOrder, String filterStatusOrder, String filterTypePayOrder) {
        this.filterIdOrder = filterIdOrder;
        this.filterStatusOrder = filterStatusOrder;
        this.filterTypePayOrder = filterTypePayOrder;
    }
}
