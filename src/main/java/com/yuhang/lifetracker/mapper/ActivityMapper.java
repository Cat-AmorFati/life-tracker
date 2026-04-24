package com.yuhang.lifetracker.mapper;

import com.yuhang.lifetracker.entity.Activity;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface ActivityMapper {
    int insert(Activity activity);
    List<Activity> findAll();
    int deleteById(Long id);
}
