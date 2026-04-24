package com.yuhang.lifetracker.service.impl;

import com.yuhang.lifetracker.entity.Activity;
import com.yuhang.lifetracker.mapper.ActivityMapper;
import com.yuhang.lifetracker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public void addActivity(Activity activity) {
        if (activity.getCreateTime() == null) {
            activity.setCreateTime(LocalDateTime.now());
        }
        activityMapper.insert(activity);
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityMapper.findAll();
    }

    @Override
    public void removeActivity(Long id) {
        activityMapper.deleteById(id);
    }
}
