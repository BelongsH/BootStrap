package com.balala.bootstrap.core;

import android.text.TextUtils;

import com.balala.bootstrap.bridge.IBootstrap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.balala.bootstrap.core.BootStrapUtils.cache2ClassInfo;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/02
 *     desc   :
 *     version: 1.0
 * </pre>
 */

@SuppressWarnings("all")
class BootStrapCore {


    //默认存放类名位置
    private static final String DEFAULT_CLASS_NAME = "className";
    //存放类的名称
    private static final String DEFAULT_NAME = "name";
    //内存缓存变量
    private static Map<String, IBootstrap> cachaObject = new HashMap<>();
    //默认的执行方法
    static final String DEFAULT_EXECUTE_NAME = "execute";


    /**
     * 从内存缓存中获取相关类的信息
     *
     * @return 相关类信息
     */
    static Map<String, List<Map<String, String>>> findClassMap2Cache() {
        return cache2ClassInfo;
    }


    /**
     * 从内存缓存中遍历数据
     *
     * @param group 分组信息
     * @return 组内的变量
     * @throws Exception
     */
    static List<IBootstrap> findListForGroupMain(String group) throws Exception {
        List<IBootstrap> result = new ArrayList<>();
        List<Map<String, String>> targetGroup = findClassMap2Cache().get(group);
        if (targetGroup == null || targetGroup.isEmpty()) return new ArrayList<>();
        for (Object item : targetGroup) {
            Map<String, String> map = (Map<String, String>) item;
            String classLocation = map.get(DEFAULT_CLASS_NAME);
            String className = map.get(DEFAULT_NAME);
            if (classLocation != null && !TextUtils.isEmpty(classLocation)) {
                IBootstrap hint = cachaObject.get(className);
                if (hint == null) {
                    Class mClass = Class.forName(classLocation);
                    hint = (IBootstrap) mClass.newInstance();
                    cachaObject.put(className, hint);
                }
                result.add(hint);
            } else {
                System.out.println("must init class");
            }
        }
        return result;
    }


    /**
     * 调用分组中的方法
     *
     * @param group 组名称
     * @param args  参数
     * @throws Exception 异常
     */
    static void invokeGroupMethod(String group, Object... args) throws Exception {
        List<IBootstrap> models = findListForGroupMain(group);
        for (IBootstrap model : models) {
            model.execute(args);
        }
    }


}
