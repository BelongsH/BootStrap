package com.balala.bootstrap.core;

import android.app.Application;

import com.balala.bootstrap.bridge.BootstrapWrapApplication;
import com.balala.bootstrap.bridge.IBootstrap;
import com.balala.bootstrap.model.BootStrapAppModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static com.balala.bootstrap.core.BootStrapCore.findClassMap2Cache;
import static com.balala.bootstrap.core.BootStrapCore.findListForGroupMain;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */

@SuppressWarnings("all")
public class BootStrapApplicationCore {


    //默认application分组名称
    public static final String DEFAULT_APP_NAME = "application";


    /**
     * app 启动的时候调用
     *
     * @param application 对象
     */
    static void invokeApplicaitonOnCreate(Application application) {
        try {
            invokeApp(application);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * APP启动的时候调用
     *
     * @param args app
     * @throws Exception
     */
    private static void invokeApp(Object... args) throws Exception {
        List<Map<String, String>> targetGroup = findClassMap2Cache().get(DEFAULT_APP_NAME);
        if (targetGroup == null || targetGroup.isEmpty()) return;

        //获取json->对象
        List<BootStrapAppModel> models = new ArrayList<>();
        List<IBootstrap> values = findListForGroupMain(DEFAULT_APP_NAME);
        for (Map fifter : targetGroup) {
            BootStrapAppModel model = BootStrapAppModel.transform(fifter);
            for (IBootstrap iBootstrap : values) {
                String name = iBootstrap.getClass().getName();
                if (name.equals(model.className)) {
                    model.bootstrapWrapApplication = (BootstrapWrapApplication) iBootstrap;
                }
            }
            models.add(model);
        }

        //对线程进行分组
        List<BootStrapAppModel> runnableExecute = new ArrayList<>();
        List<BootStrapAppModel> mainThreadExecute = new ArrayList<>();
        for (BootStrapAppModel model : models) {
            boolean temp = model.isMain.equals("1") ? mainThreadExecute.add(model) : runnableExecute.add(model);
        }
        Collections.sort(runnableExecute, new ComparatorWarp());
        Collections.sort(mainThreadExecute, new ComparatorWarp());
        //执行方法,主函数
        for (BootStrapAppModel model : mainThreadExecute) {
            executeMethod(model.bootstrapWrapApplication, args);
        }

        //执行方法，异步函数
        new AppExecutors().diskIO().execute(() -> {
            for (BootStrapAppModel model : runnableExecute) {
                executeMethod(model.bootstrapWrapApplication, args);
            }
        });
    }


    /**
     * 反射执行方法
     *
     * @param obj  执行对象
     * @param args 执行参数
     */
    private static void executeMethod(IBootstrap model, Object... args) {
        model.execute(args);
    }


    /**
     * 比较器
     */
    static class ComparatorWarp implements Comparator<BootStrapAppModel> {

        @Override
        public int compare(BootStrapAppModel o1, BootStrapAppModel o2) {
            return o2.priority - o1.priority;
        }
    }
}
