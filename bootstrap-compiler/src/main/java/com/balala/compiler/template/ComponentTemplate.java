package com.balala.compiler.template;

import androidx.collection.ArrayMap;

import com.balala.bootstrap.annotation.Component;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/11/28
 *     desc   : 处理 Component 生成的代码
 *     version: 1.0
 * </pre>
 */
public class ComponentTemplate {

    /***
     * 默认创建包名
     */
    private static final String PACKAGE_PATH = "com.balala.ico";
    /**
     * 默认创建类名
     */
    private static final String CLASS_NAME = "WareStorage";

    private static final String VARIABLE_MAP = "models";

    /**
     * 方法前缀，用于动态创建方法
     */
    private static final String METHOD_PREFIX = "put2Storage";


    /**
     * 动态存储方法
     */
    private List<MethodSpec> methods = new ArrayList<>();

    public void loopAddToMap(TypeElement originalType) {
        Component component = originalType.getAnnotation(Component.class);
        methods.add(loopAnnotationCreateMethod(component, originalType));
    }


    public void writeFile(Filer filer) {
        //泛型属性
        ParameterizedTypeName a = ParameterizedTypeName.get(Map.class, String.class, String.class);
        ClassName listClassName = ClassName.get(List.class);
        ParameterizedTypeName b = ParameterizedTypeName.get(listClassName, a.box());
        ClassName mapClassName = ClassName.get(Map.class);
        ClassName StringClassName = ClassName.get(String.class);
        ParameterizedTypeName parameterizedTypeName = ParameterizedTypeName.get(mapClassName, StringClassName, b.box());
        //字符串
        TypeSpec.Builder builder = TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addField(FieldSpec.builder(parameterizedTypeName, VARIABLE_MAP, Modifier.PRIVATE, Modifier.FINAL).build())
                .addField(createInstanceField());
        wrapMethodInfo(builder);
        JavaFile javaFile = JavaFile.builder(PACKAGE_PATH, builder.build()).build();
        try {
            javaFile.writeTo(filer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 包装类中的方法
     *
     * @param builder 类构建器
     */
    private void wrapMethodInfo(TypeSpec.Builder builder) {
        //创建构造方法
        MethodSpec.Builder constructor = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addStatement("this." + VARIABLE_MAP + " = new $T<$T,$T<$T<$T,$T>>>()", HashMap.class, String.class, List.class, Map.class, String.class, String.class);
        for (int i = 0; i < methods.size(); i++) {
            constructor.addStatement("this." + METHOD_PREFIX + +i + "()");
        }

        builder.addMethod(constructor.build());
        builder.addMethod(createJudgeList());
        builder.addMethod(createInstanceMethod());
        for (MethodSpec method : methods) {
            builder.addMethod(method);
        }
    }


    /***
     *  循环注解，根据注解解析相关参数。
     * @param component 注解信息
     * @param originalType 原始类型
     * @return 方法体
     */
    private MethodSpec loopAnnotationCreateMethod(Component component, TypeElement originalType) {
        MethodSpec.Builder targetMethod = MethodSpec.methodBuilder(METHOD_PREFIX + methods.size()).addModifiers(Modifier.PRIVATE);
        targetMethod.addStatement("$T map=new $T()", Map.class, HashMap.class);
        targetMethod.addStatement("map.put($S,$S)", "name", component.name());
        targetMethod.addStatement("map.put($S,$S)", "class", originalType.getQualifiedName().toString());
        targetMethod.addStatement("this.judgeEmptyList($S).add(map)", component.group());
        return targetMethod.build();
    }


    /***
     * 创建单例对象
     * @return filed
     */
    private FieldSpec createInstanceField() {
        ClassName targetClass = ClassName.get(PACKAGE_PATH, CLASS_NAME);
        return FieldSpec.builder(targetClass, "instance", Modifier.PRIVATE, Modifier.FINAL, Modifier.STATIC).initializer("new $T()", targetClass)
                .build();
    }

    /**
     * 创建单例方法
     *
     * @return method
     */
    private MethodSpec createInstanceMethod() {
        ClassName targetClass = ClassName.get(PACKAGE_PATH, CLASS_NAME);
        MethodSpec.Builder method = MethodSpec.methodBuilder("getInstance")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.STATIC)
                .addStatement("return instance")
                .returns(targetClass);
        return method.build();
    }


    /***
     *  获取 map 中的数据
     * @return 方法体
     */
    private MethodSpec createJudgeList() {
        MethodSpec.Builder constructor = MethodSpec.methodBuilder("judgeEmptyList")
                .addModifiers(Modifier.PUBLIC)
                .returns(List.class)
                .addParameter(String.class, "group")
                .addStatement("List data=this." + VARIABLE_MAP + ".get(group)")
                .addStatement("if(data==null){$T list = new $T(); this." + VARIABLE_MAP + ".put(group, list); return list;}  return data", List.class, ArrayList.class);
        return constructor.build();
    }


}
