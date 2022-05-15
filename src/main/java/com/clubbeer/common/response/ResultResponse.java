package com.clubbeer.common.response;

import lombok.Data;

import java.util.List;
@Data
public class ResultResponse<T> {
    private List<T> data;
    private long count;

    public ResultResponse(List<T> data, long count) {
        this.data = data;
        this.count = count;
    }
}
