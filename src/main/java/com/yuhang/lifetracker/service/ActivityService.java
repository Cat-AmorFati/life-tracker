package com.yuhang.lifetracker.service;

import com.yuhang.lifetracker.entity.Activity;
import java.util.List;

public interface ActivityService {
    void addActivity(Activity activity);
    List<Activity> getAllActivities();
    void removeActivity(Long id);
}
