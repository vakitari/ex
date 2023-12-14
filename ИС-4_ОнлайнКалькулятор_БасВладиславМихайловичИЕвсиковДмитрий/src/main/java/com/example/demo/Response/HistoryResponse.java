package com.example.demo.Response;

import com.example.demo.Entity.History;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HistoryResponse extends BaseResponse {
    public HistoryResponse(Iterable<History> data)  {
        super(true, "История операций");
        this.data = data;
    }

    private Iterable<History> data;
}
