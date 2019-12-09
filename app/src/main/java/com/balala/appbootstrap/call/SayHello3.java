package com.balala.appbootstrap.call;

import android.app.Activity;
import android.widget.Toast;

import com.balala.bootstrap.annotation.Component;
import com.balala.bootstrap.bridge.IBootstrap;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
@Component(group = "sayHello", name = "SayHello3")
public class SayHello3 implements IBootstrap {

    @Override
    public void execute(Object... args) {
        if (args[0] instanceof Activity) {
            Activity activity = (Activity) args[0];
            Toast.makeText(activity, "hello world SayHello3", Toast.LENGTH_SHORT).show();
        }
    }
}
