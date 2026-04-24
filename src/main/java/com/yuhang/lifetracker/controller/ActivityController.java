package com.yuhang.lifetracker.controller;

import com.yuhang.lifetracker.entity.Activity;
import com.yuhang.lifetracker.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@CrossOrigin(origins = "*")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @PostMapping
    public String add(@RequestBody Activity activity) {
        activityService.addActivity(activity);
        return "Activity added successfully!";
    }

    @GetMapping
    public List<Activity> list() {
        return activityService.getAllActivities();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        activityService.removeActivity(id);
        return "Activity deleted successfully!";
    }
}
