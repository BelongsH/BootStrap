package com.balala.appbootstrap.app;

import android.app.Application;

import com.balala.bootstrap.annotation.BootStrapApp;
import com.balala.bootstrap.bridge.BootStrapProxy;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@BootStrapApp(name = "HelloApp4",priority = 5,isMainThread = false)
public class HelloApp4 extends BootStrapProxy {
    @Override
    public void onCreate(Application application) {
        System.out.println("HelloApp4");
//        Toast.makeText(application, "HelloApp4", Toast.LENGTH_SHORT).show();
    }
}
