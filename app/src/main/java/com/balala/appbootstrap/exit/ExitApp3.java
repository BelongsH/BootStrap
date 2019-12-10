package com.balala.bootstrapdemo.exit;

import android.app.Activity;
import android.widget.Toast;

import com.balala.bootstrap.annotation.Component;
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

@Component(name = "ExitApp3", group = "exit")
public class ExitApp3 implements IBootstrap {
    @Override
    public void execute(Object... args) {
        if (args[0] != null && args[0] instanceof Activity) {
            Activity activity = (Activity) args[0];
            Toast.makeText(activity, "ExitApp3", Toast.LENGTH_SHORT).show();
        }
    }
}
