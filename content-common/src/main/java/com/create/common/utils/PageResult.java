package com.create.common.utils;

import lombok.Data;

import java.util.List;

/**
 * 分页结果封装
 *
 * @author xmy
 * @date 2021/2/1 16:04
 */
@Data
public class PageResult<T> {

    /**
     * 页码
     */
    private int page;

    /**
     * 每页记录数
     */
    private int limit;

    /**
     * 记录总数
     */
    private long total;

    /**
     * 总页数
     */
    private long totalPages;

    /**
     * 是否有更多数据
     */
    private boolean hasMore;

    /**
     * 每页数据列表
     */
    private List<T> records;;
}