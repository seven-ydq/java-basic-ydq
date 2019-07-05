package com.ydq.util;

import java.util.List;
import java.util.Map;

public class PageUtil {
    private int pageNumber;//要查的页面
    private int perPageNumber = 5;//每页显示的数据数
    private int totalDataNumber;//总记录数
    private int totalPageNumber;//总页数
    private List<Map<String, Object>> pageDataList;//每页显示的数据

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPerPageNumber() {
        return perPageNumber;
    }

    public void setPerPageNumber(int perPageNumber) {
        this.perPageNumber = perPageNumber;
    }

    public int getTotalDataNumber() {
        return totalDataNumber;
    }

    public void setTotalDataNumber(int totalDataNumber) {
        this.totalDataNumber = totalDataNumber;
    }

    public int getTotalPageNumber() {
        return totalPageNumber;
    }

    public void setTotalPageNumber(int totalPageNumber) {
        this.totalPageNumber = totalPageNumber;
    }

    public List<Map<String, Object>> getPageDataList() {
        return pageDataList;
    }

    public void setPageDataList(List<Map<String, Object>> pageDataList) {
        this.pageDataList = pageDataList;
    }
}
