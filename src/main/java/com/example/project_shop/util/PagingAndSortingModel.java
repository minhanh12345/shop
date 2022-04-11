package com.example.project_shop.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingAndSortingModel<TCriteria> {
    int pageIndex;
    int pageSize;
    String filterColumn;
    String filterValue;
    String sortColumn;
    String sortDirection;
    SearchCriteria searchCriteria;
    private TCriteria criteria;
    public PagingAndSortingModel(TCriteria criteria, int pageIndex, int pageSize, String sortColumn, String sortDirection) {
        this.criteria = criteria;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.sortColumn = sortColumn;
        this.sortDirection = sortDirection;
    }
    public PagingAndSortingModel(SearchCriteria searchOrgCriteria, int pageIndex, int pageSize, String sortColumn, String sortDirection) {
        this.searchCriteria = searchOrgCriteria;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.sortColumn = sortColumn;
        this.sortDirection = sortDirection;
    }

}
