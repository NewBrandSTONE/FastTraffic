package com.dahua.oz.t_compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * 用来遍历我们传入对象，类中的注解
 *
 * @author O.z Young
 * @version 2018/4/16
 */

public final class PayVisitor extends SimpleAnnotationValueVisitor7<Void, Void> {

    /**
     * 需要遍历的东西
     */
    private Filer mFiler = null;
    /**
     * 通过循环找出来的类型
     */
    private TypeMirror mTypeMirror = null;
    /**
     * 最终需要拿到的包名
     */
    private String mPackageName = null;

    public void setFiler(Filer filer) {
        this.mFiler = filer;
    }

    @Override
    public Void visitString(String s, Void aVoid) {
        mPackageName = s;
        return aVoid;
    }

    @Override
    public Void visitType(TypeMirror typeMirror, Void aVoid) {
        mTypeMirror = typeMirror;
        generateJavaCode();
        return aVoid;
    }

    private void generateJavaCode() {
        final TypeSpec wxEntryActivity = TypeSpec.classBuilder("WXPayEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror))
                .build();

        final JavaFile javaFile = JavaFile.builder(mPackageName + ".wxapi", wxEntryActivity)
                .addFileComment("微信支付入口文件")
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
