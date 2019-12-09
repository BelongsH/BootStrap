package com.balala.bootstrap.core;

import android.app.Application;

import com.balala.bootstrap.utils.AssetsUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/04
 *     desc   : 获取文件
 *     version: 1.0
 * </pre>
 */
class BootStrapUtils {

    /**
     * 文件路径
     */
    private static final String DIR_PATH = "bootstrap";

    public static Application app;

    static Map<String, List<Map<String, String>>> cache2ClassInfo = new HashMap<>();


    /**
     * 获取配置文件的信息，填充到内存缓存中
     *
     * @param application 上下文对象
     */
    static void init(Application application) {
        app = application;
        try {
            String[] files = application.getAssets().list(DIR_PATH);
            Gson gson = new Gson();
            if (files != null) {
                for (String fileName : files) {
                    String json = AssetsUtils.readAssets2String(DIR_PATH + "/" + fileName, application);
                    Map<String, List<Map<String, String>>> values = gson.fromJson(json, new TypeToken<Map<String, List<Map<String, String>>>>() {
                    }.getType());
                    for (Map.Entry<String, List<Map<String, String>>> model : values.entrySet()) {
                        String key = model.getKey();
                        List<Map<String, String>> groupValue = cache2ClassInfo.get(key);
                        if (groupValue == null) {
                            cache2ClassInfo.put(key, model.getValue());
                        } else {
                            groupValue.addAll(model.getValue());
                            cache2ClassInfo.put(key, groupValue);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
