package com.cn.music.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date: 2018-06-26 上午11:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PathReq {
    private String sourcePath;
    private String targetPath;
    private String fileType;
}
