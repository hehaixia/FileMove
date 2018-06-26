package com.cn.music.controller;

import com.cn.music.service.IMusicService;
import com.cn.music.web.request.PathReq;
import me.ele.ping.common.web.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date: 2018-06-26 上午11:31
 */
@RestController
@RequestMapping(value = "/music")
public class MusicController {

    @Autowired
    private IMusicService musicService;

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    public Response move(@RequestBody PathReq req) {
        return musicService.move(req);
    }
}
