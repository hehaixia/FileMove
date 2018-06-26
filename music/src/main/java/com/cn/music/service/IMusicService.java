package com.cn.music.service;

import com.cn.music.web.request.PathReq;
import me.ele.ping.common.web.Response;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date: 2018-06-26 上午11:39
 */
public interface IMusicService {
    Response move(PathReq req);
}
