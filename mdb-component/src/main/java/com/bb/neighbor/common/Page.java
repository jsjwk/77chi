package com.bb.neighbor.common;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int total;// 总记录数
    private int pageSize = 10;// 每页显示的数据条数
    private int curPage = 1;// 当前页码
    private List<T> datas;// 保存查询结果

    public Page(int pageSize, int curPage, List<T> datas) {
        this.pageSize = pageSize;
        this.curPage = curPage;
        this.datas = datas;
    }

    public Page(int total, int pageSize, int curPage, List<T> datas) {
        this.total = total;
        this.pageSize = pageSize;
        this.curPage = curPage;
        this.datas = datas;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}
