package com.balala.bootstrap.core;

import com.balala.bootstrap.bridge.IBootstrap;

import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/02
 *     desc   : 提供给外部调用
 *     version: 1.0
 * </pre>
 */
public final class BootStrap {

    public static List<IBootstrap> findListForGroup(String group) {
        try {
            return BootStrapCore.findListForGroupMain(group);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public static void invokeGroupMethod(String group, Object... args) {
        try {
            BootStrapApplicationCore.invokeMethod(group, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    /***
//     *  根据组名，获取该组实例
//     * @param group  组名
//     * @return 组实例
//     */

//
//
//    /***
//     *  直接调用相关信息
//     * @param group 相关组的信息
//     */
//    public static void invokeGroupMethod(String group, Object... args) {
//        try {
//            BootStrapCore.invokeGroupMethod(group, args);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


//    /***
//     *  根据组名，获取该实例
//     * @param name 实例名称
//     * @return 组实例
//     */
//    public static IBootstrap findListForName(String name) {
//        try {
//            return BootStrapCore.findListForGroupMain(group);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ArrayList<>();
//    }


}
