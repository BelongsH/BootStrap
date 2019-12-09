package com.balala.bootstrap.bridge;

import android.app.Application;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/05
 *     desc   : 对application进行包装
 *     version: 1.0
 * </pre>
 */
public abstract class BootstrapWrapApplication implements IBootstrap {


    /**
     * 包装类
     *
     * @param args 参数信息
     */
    public void execute(Object... args) {
        if (args != null && args[0] instanceof Application) {
            Application application = (Application) args[0];
            onCreate(application);
        }
    }

    public abstract void onCreate(Application application);
}
