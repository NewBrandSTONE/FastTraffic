package com.dahua.oz.t_compiler.compiler;

import com.dahua.oz.t_annotations.annotations.AppRegisterGenerator;
import com.dahua.oz.t_annotations.annotations.EntryGenerator;
import com.dahua.oz.t_annotations.annotations.PayEntryGenerator;
import com.google.auto.service.AutoService;

import java.lang.annotation.Annotation;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.AnnotationValueVisitor;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

/**
 * 模仿ButterKnife自定义解析器
 *
 * @author O.z Young
 * @version 2018/4/16
 */
@AutoService(Process.class)
@SuppressWarnings("unused")
public class TrafficProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        generateEntryCode(roundEnvironment);
        generatePayCode(roundEnvironment);
        generateRegisterCode(roundEnvironment);
        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        final Set<String> types = new LinkedHashSet<>();
        final Set<Class<? extends Annotation>> supportAnnotations = getSupportedAnnotations();
        for (Class<? extends Annotation> anotation : supportAnnotations) {
            types.add(anotation.getCanonicalName());
        }
        // 获取在程序中已经注解的类
        return types;
    }

    private Set<Class<? extends Annotation>> getSupportedAnnotations() {
        final Set<Class<? extends Annotation>> anotations = new LinkedHashSet<>();
        anotations.add(EntryGenerator.class);
        anotations.add(PayEntryGenerator.class);
        anotations.add(AppRegisterGenerator.class);
        return anotations;
    }

    /**
     * 扫描每一个类上我们注解的东西
     *
     * @param evn
     * @param annotation
     * @param visitor
     */
    private void scan(RoundEnvironment evn,
                      Class<? extends Annotation> annotation,
                      AnnotationValueVisitor visitor) {
        for (Element typeElement : evn.getElementsAnnotatedWith(annotation)) {
            final List<? extends AnnotationMirror> annotationMirrors = typeElement.getAnnotationMirrors();
            for (AnnotationMirror mirror : annotationMirrors) {
                final Map<? extends ExecutableElement, ? extends AnnotationValue> elementValues
                        = mirror.getElementValues();

                for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : elementValues.entrySet()) {
                    entry.getValue().accept(visitor, null);
                }
            }
        }
    }

    private void generateEntryCode(RoundEnvironment env) {
        final EntryVisitor entryVisitor = new EntryVisitor();
        entryVisitor.setFiler(processingEnv.getFiler());
        scan(env, EntryGenerator.class, entryVisitor);
    }

    private void generatePayCode(RoundEnvironment env) {
        final PayVisitor visitor = new PayVisitor();
        visitor.setFiler(processingEnv.getFiler());
        scan(env, PayEntryGenerator.class, visitor);
    }

    private void generateRegisterCode(RoundEnvironment env) {
        final AppRegisterVisitor visitor = new AppRegisterVisitor();
        visitor.setFiler(processingEnv.getFiler());
        scan(env, AppRegisterGenerator.class, visitor);
    }
}
