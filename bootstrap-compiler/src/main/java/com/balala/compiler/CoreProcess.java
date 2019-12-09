package com.balala.compiler;

import com.balala.bootstrap.annotation.BootStrapApp;
import com.balala.bootstrap.annotation.Component;
import com.balala.compiler.template.BootStrapAppBuilder;
import com.balala.compiler.template.ComponentBuilder;
import com.google.auto.service.AutoService;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class CoreProcess extends BaseProcessor {


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(Component.class.getCanonicalName());
        annotations.add(BootStrapApp.class.getCanonicalName());
        return annotations;
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        handleComponent(roundEnvironment);
        handleBootStrapApp(roundEnvironment);
        return true;
    }

    private void handleComponent(RoundEnvironment roundEnvironment) {
        HashMap<TypeElement, List<Element>> models = parseValue(roundEnvironment, Component.class);
        ComponentBuilder template = new ComponentBuilder(mEnvironment);
        for (Map.Entry<TypeElement, List<Element>> entry : models.entrySet()) {
            template.loopAddToMap(entry.getKey());
        }
        if (models.size() != 0) {
            template.writeFile(mEnvironment.getFiler(), template.getMap());
        }
    }

    private void handleBootStrapApp(RoundEnvironment roundEnvironment) {
        HashMap<TypeElement, List<Element>> models = parseValue(roundEnvironment, BootStrapApp.class);
        BootStrapAppBuilder template = new BootStrapAppBuilder(mEnvironment);
        for (Map.Entry<TypeElement, List<Element>> entry : models.entrySet()) {
            template.loopAddToMap(entry.getKey());
        }
        if (models.size() != 0) {
            template.writeFile(mEnvironment.getFiler(), template.getMap());
        }
    }


}

