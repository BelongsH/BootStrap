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

@Component(name = "ExitApp2", group = "exit")
public class ExitApp2 implements IBootstrap {
    @Override
    public void execute(Object... args) {
        if (args[0] != null && args[0] instanceof Activity) {
            Activity activity = (Activity) args[0];
            Toast.makeText(activity, "ExitApp2", Toast.LENGTH_SHORT).show();
        }
    }
}
