package com.balala.compiler.template;

import androidx.collection.ArrayMap;

import com.balala.bootstrap.annotation.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/11/28
 *     desc   : 处理 Component 生成的代码
 *     version: 1.0
 * </pre>
 */
public class ComponentBuilder extends JsonWriterTemplate {

    private static final String DEFAULT_NAME = "com.balala.bootstrap.bridge.IBootstrap";


    public ComponentBuilder(ProcessingEnvironment processingEnvironment) {
        this.processingEnvironment = processingEnvironment;
        types = processingEnvironment.getTypeUtils();
    }


    private Map<String, List<Map<String, String>>> map = new HashMap<>();


    public void loopAddToMap(TypeElement originalType) {
        if (!isSubtype(originalType.asType(), DEFAULT_NAME)) {
            this.processingEnvironment.getMessager().printMessage(Diagnostic.Kind.ERROR, "------标记为Component的注解必须实现IBootstrap接口-----"+originalType.asType());
            return;
        }
        Component component = originalType.getAnnotation(Component.class);
        List<Map<String, String>> models = map.get(component.group());
        if (models == null) {
            models = new ArrayList<>();
            map.put(component.group(), models);
        }
        Map<String, String> elements = new ArrayMap<>();
        elements.put("name", component.name());
        elements.put("className", originalType.getQualifiedName().toString());
        models.add(elements);
    }

    public Map<String, List<Map<String, String>>> getMap() {
        return map;
    }
}
