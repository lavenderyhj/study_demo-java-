package com.yu.jobs.controller;

import com.yu.jobs.Service.SchedulerService;
import com.yu.jobs.Service.SchedulerService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuhuijuan on 2018/5/22
 */
@RestController
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;
    @Autowired
    private SchedulerService2 schedulerService2;

    @RequestMapping("/start")
    public String start(@RequestParam int id) {
        if (id == 1) {
            schedulerService.start();
        } else if (id == 2) {
            schedulerService2.start();
        }
        return id + " start ok";
    }

    @RequestMapping("/stop")
    public String stop(@RequestParam int id) {
        if (id == 1) {
            schedulerService.stop();
        } else if (id == 2) {
            schedulerService2.stop();
        }
        return id + " stop ok";
    }

    @RequestMapping("/clear")
    public String clear(@RequestParam int id) {
        if (id == 1) {
            schedulerService.clear();
        } else if (id == 2) {
            schedulerService2.clear();
        }
        return id + " clear ok";
    }

    @RequestMapping("/restart")
    public String restart(@RequestParam int id) {
        if (id == 1) {
            schedulerService.reStart();
        } else if (id == 2) {
            schedulerService2.reStart();
        }
        return id + "restart ok";
    }

    @RequestMapping("/end")
    public String end() {
        schedulerService.end();
        return "end ok";
    }

    @RequestMapping("/cron")
    public String cron(@RequestParam int id, @RequestParam String cron) {
        if (id == 1) {
            schedulerService.setCron(cron);
        } else if (id == 2) {
            schedulerService2.setCron(cron);
        }
        return id + "cron ok";
    }

    @RequestMapping("/isRunning")
    public String isRunning(@RequestParam int id) {
        boolean isRunning = false;
        if (id == 1) {
            isRunning = schedulerService.isRunning();
        } else if (id == 2) {
            isRunning = schedulerService2.isRunning();
        }
        return id + String.valueOf(isRunning);
    }
}
