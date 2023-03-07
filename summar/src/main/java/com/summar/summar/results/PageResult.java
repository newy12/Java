package com.summar.summar.results;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

@Slf4j
public class PageResult {
    public static ResponseEntity<?> build(Page<?> page) {
        ApiResult apiResult = ApiResult.blank()
                .add("totalRecordCount", page.getTotalElements())
                .add("totalPageCount", page.getTotalPages())
                .add("firstPage", page.isFirst())
                .add("lastPage", page.isLast())
                .add("currentPageNo", page.getNumber() + 1)
                .add("recordsPerPage", page.getSize())
                .add("content", page.getContent());
        return Result.ok(apiResult);
    }
}
