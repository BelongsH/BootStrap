package com.balala.appbootstrap;

import android.app.Application;
import android.widget.Toast;

import com.balala.bootstrap.annotation.Component;
import com.balala.bootstrap.bridge.BootStrapProxy;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/11/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */

@Component(name = "hello4", group = "list")
public class YouMengInit4 extends BootStrapProxy {

    @Override
    public void onCreate(Application application) {
        Toast.makeText(application, "YouMengInit4", Toast.LENGTH_SHORT).show();
    }

}
