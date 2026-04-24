package com.yuhang.lifetracker.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Activity {
    private Long id;
    private String content;
    private String category;
    private LocalDateTime createTime;
}
