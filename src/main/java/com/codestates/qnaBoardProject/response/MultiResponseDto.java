package com.codestates.qnaBoardProject.response;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
@Getter
public class MultiResponseDto<T> {
    private List<T> data;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> data, Page page) {
        this.data = data;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                pageInfo.getSize(), pageInfo.getTotalElements(), pageInfo.getTotalPages());
    }
}
