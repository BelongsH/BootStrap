package com.balala.appbootstrap.exit;

import android.app.Activity;
import android.widget.Toast;

import com.balala.bootstrap.annotation.BootStrapApp;
import com.balala.bootstrap.bridge.IBootstrap;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : liuhuiliang@lexiangbao.com
 *     time   : 2019/12/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */

@BootStrapApp(name = "hello1", priority = 99, group = "exit")
public class Hello1 implements IBootstrap {
    @Override
    public void execute(Object... args) {
        if (args[0] instanceof Activity) {
            Activity activity = (Activity) args[0];
            Toast.makeText(activity, "ExitApp2", Toast.LENGTH_SHORT).show();
        }
    }
}
