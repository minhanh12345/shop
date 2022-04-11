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

    public SearchCriteria(String filterVegType, String filterNameVeg, String filterVegStatus, String filterVegDiscount) {
        this.filterVegType = filterVegType;
        this.filterNameVeg = filterNameVeg;
        this.filterVegStatus = filterVegStatus;
        this.filterVegDiscount = filterVegDiscount;
    }

    public boolean isEmptyFilters() {
        return CommonUtil.stringIsNullOrEmpty(filterNameVeg) &&
                CommonUtil.stringIsNullOrEmpty(filterVegType) &&
                CommonUtil.stringIsNullOrEmpty(filterVegStatus) &&
                CommonUtil.stringIsNullOrEmpty(filterVegDiscount);
    }
}
