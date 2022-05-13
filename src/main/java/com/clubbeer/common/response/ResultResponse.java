package com.clubbeer.common.response;

import java.util.List;

public class ResultResponse<T> {
    private List<T> data;
    private Integer count;

    public ResultResponse(List<T> data, Integer count) {
        this.data = data;
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
