package com.cxy.website.common.util;

import com.cxy.website.common.CommonStatus;

import java.io.File;

/**
 * @program: website
 * @description: 检查封面
 * @author: CuiXiangYu
 * @create: 2021-12-15 18:46
 **/
public class CoverUtil {

    public void checkExist(){
        String coverAddr = CommonStatus.FILE_COVER_PREFIX;
        File file = new File(coverAddr);
        if(!file.exists()){
            return;
        }

    }
}
