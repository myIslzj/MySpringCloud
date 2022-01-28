package com.lzj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private int id;
    private String serial;
    private String main = "主干创建";
    private String branch = "分支创建";


}
