package com.cn.music.service;

import com.cn.music.util.FileUtil;
import com.cn.music.web.request.PathReq;
import me.ele.ping.common.web.Response;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Date: 2018-06-26 上午11:38
 */
@Service
public class MusicServiceImpl implements IMusicService {
    @Override
    public Response move(PathReq req) {
        String path = req.getSourcePath();
        File file = new File(path);
        ergodic(file, new ArrayList<>(), req.getTargetPath(),req.getFileType());
        return Response.success();
    }

    private List<String> ergodic(File file, List<String> resultFileName, String target,String fileType) {
        File targetFile = new File(target);
        File[] files = file.listFiles();
        if (files == null) return resultFileName;// 判断目录下是不是空的
        int i = 0;
        int sum = 0;
        for (File f : files) {
            if (f.isDirectory()) {// 判断是否文件夹
                resultFileName.add(f.getPath());
                ergodic(f, resultFileName, target,fileType);// 调用自身,查找子目录
            } else {
                f = new File(f.getPath());
                String fileName = f.getName();
                if (!fileName.contains(".")) {
                    continue;
                } else {
                    int position = fileName.lastIndexOf(".");
                    if (fileName.substring(position + 1, fileName.length()).equalsIgnoreCase(fileType)) {
                        try {
                            targetFile = new File(target + fileName);
                            FileUtil.copyFile(f.getPath(), targetFile.getPath());
                            sum += i++;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        System.out.println("成功移动" + sum + "个文件");
        return resultFileName;
    }
}
