package com.ws.controller.modules;

import com.ws.modules.thread.service.IThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangshuo
 * @Date: 2022/6/2 10:40
 */
@RestController
@RequestMapping("/api/thread")
public class ThreadController {


    @Autowired
    private IThreadService threadService;

    @GetMapping("/test")
    public void testThreadPool() throws InterruptedException {
        threadService.testThreadPool();
    }
}
