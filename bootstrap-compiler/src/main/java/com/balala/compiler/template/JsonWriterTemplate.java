package com.balala.compiler.template;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import static jdk.nashorn.internal.objects.NativeMath.log;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/12/05
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class JsonWriterTemplate {

    ProcessingEnvironment processingEnvironment;

    Types types;


    private static final String ASSET_PATH = "assets/bootstrap/";

    private static final String FILE_SUFFIX = ".json";


    public void writeFile(Filer filer, Map map) {
        try {
            String content = new Gson().toJson(map);
            String path = ASSET_PATH + UUID.randomUUID().toString().replaceAll("-", "") + FILE_SUFFIX;
            FileObject fileObject = filer.createResource(StandardLocation.CLASS_OUTPUT, "", path);
            Writer writer = fileObject.openWriter();
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    boolean isSubtype(TypeMirror tm, String type) {
        boolean isSubType = false;
        while (tm != null) { //循环获取父类信息
            if (type.equals(tm.toString())) { //通过全路径是否相等
                isSubType = true;
                break;
            }
            TypeElement superTypeElem = (TypeElement) types.asElement(tm);
            if (superTypeElem != null) {
                if (superTypeElem.getInterfaces() != null && !superTypeElem.getInterfaces().isEmpty()) {
                    List<? extends TypeMirror> aa = superTypeElem.getInterfaces();
                    for (TypeMirror typeMirror : aa) {
                        if (type.equals(typeMirror.toString())) { //通过全路径是否相等
                            isSubType = true;
                            tm = null;
                            break;
                        }
                    }
                } else {
                    tm = superTypeElem.getSuperclass();
                }
//                tm = superTypeElem.getSuperclass();
            } else { //如果为空, 说明没了父类, 所以直接退出
                break;
            }
        }
        return isSubType;
    }

}
