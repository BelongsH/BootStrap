package com.balala.compiler;

import com.balala.bootstrap.annotation.Component;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public abstract class BaseProcessor extends AbstractProcessor {

    /**
     * 用于创建文件
     */
    protected ProcessingEnvironment mEnvironment;
    /**
     * 用于打印信息
     */
    protected Messager mMessager;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mEnvironment = processingEnvironment;
        mMessager = processingEnvironment.getMessager();

    }


    /**
     * 输出信息
     */
    protected void printMessage(Element e, String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args), e);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    protected HashMap<TypeElement, List<Element>> parseValue(RoundEnvironment roundEnvironment, Class<? extends Annotation> targetClss) {
        HashMap<TypeElement, List<Element>> datas = new HashMap<>();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(targetClss)) {
            if (element.getKind() != ElementKind.CLASS) {
                printMessage(element, "Only classes can be annotated with @%s",targetClss.getSimpleName());
                return datas;
            }
            TypeElement originalType = (TypeElement) element;
            if (datas.get(originalType) == null) {
                List<Element> elements = new ArrayList<>();
                datas.put(originalType, elements);
            }
            List<Element> dd = datas.get(originalType);
            dd.add(element);
        }
        return datas;
    }

}
