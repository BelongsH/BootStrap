package com.balala.appbootstrap.app;

import android.app.Application;
import android.widget.Toast;

import com.balala.bootstrap.annotation.BootStrapApp;
import com.balala.bootstrap.bridge.BootstrapWrapApplication;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@BootStrapApp(name = "HelloApp2", priority = 1)
public class HelloApp2 extends BootstrapWrapApplication {
    @Override
    public void onCreate(Application application) {
        Toast.makeText(application, "HelloApp2", Toast.LENGTH_SHORT).show();
    }
}
