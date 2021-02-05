package com.xxcw.allowance.bean;

import lombok.Data;

@Data
public class QueryInfo {
    private String query;//查询信息 username
    private int pageNum=1;//当前页
    private int pageSize=1;//每页数据行数
}
