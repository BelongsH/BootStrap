package com.balala.bootstrap.model;

import com.balala.bootstrap.bridge.BootstrapWrapApplication;

import java.util.Map;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/05
 *     desc   : json进行解析
 *     version: 1.0
 * </pre>
 */
public class BootStrapAppModel {
    public String isMain;
    public int priority;
    public String name;
    public String className;
    public BootstrapWrapApplication bootstrapWrapApplication;


    public static BootStrapAppModel transform(Map<String, String> bean) {
        BootStrapAppModel model = new BootStrapAppModel();
        model.isMain = bean.get("isMain");
        model.priority = Integer.parseInt(bean.get("priority"));
        model.name = bean.get("name");
        model.className = bean.get("className");
        return model;
    }


}
