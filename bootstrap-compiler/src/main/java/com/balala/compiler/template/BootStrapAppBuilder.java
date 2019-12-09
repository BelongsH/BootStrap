package com.balala.compiler.template;

import androidx.collection.ArrayMap;

import com.balala.bootstrap.annotation.BootStrapApp;
import com.balala.bootstrap.annotation.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

import static jdk.nashorn.internal.objects.NativeMath.log;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/11/28
 *     desc   : 处理 Component 生成的代码
 *     version: 1.0
 * </pre>
 */
public class BootStrapAppBuilder extends JsonWriterTemplate {


    private Map<String, List<Map<String, String>>> map = new HashMap<>();

    private static final String DEFAULT_NAME = "com.balala.bootstrap.bridge.BootstrapWrapApplication";


    public BootStrapAppBuilder(ProcessingEnvironment processingEnvironment) {
        this.processingEnvironment = processingEnvironment;
        types = processingEnvironment.getTypeUtils();
    }


    public void loopAddToMap(TypeElement originalType) {
        if (!isSubtype(originalType.asType(), DEFAULT_NAME)) {
            this.processingEnvironment.getMessager().printMessage(Diagnostic.Kind.ERROR, "------标记为BootStrapApp的注解必须实现BootstrapWrapApplication接口-----" + originalType.asType());
            return;
        }
        BootStrapApp bootStrapApp = originalType.getAnnotation(BootStrapApp.class);
        List<Map<String, String>> models = map.get(bootStrapApp.group());
        if (models == null) {
            models = new ArrayList<>();
            map.put(bootStrapApp.group(), models);
        }
        Map<String, String> elements = new ArrayMap<>();
        elements.put("name", bootStrapApp.name());
        elements.put("className", originalType.getQualifiedName().toString());
        elements.put("priority", String.valueOf(bootStrapApp.priority()));
        elements.put("isMain", bootStrapApp.isMainThread() ? "1" : "0");
        models.add(elements);
    }


    public Map<String, List<Map<String, String>>> getMap() {
        return map;
    }


}
