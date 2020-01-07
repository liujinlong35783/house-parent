package com.house.utils;

/**
 * Created by HP on 2019/12/20.
 */
public class Page extends Some{
    private Integer page=1;
    private Integer rows=3;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
