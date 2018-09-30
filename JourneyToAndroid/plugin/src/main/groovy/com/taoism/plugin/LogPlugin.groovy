package com.taoism.plugin

import com.taoism.javassist.transform.ASMTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

public class LogPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        System.out.println("------------------开始----------------------");
        System.out.println("这是我们的自定义插件!");

        def log = project.logger
        log.error "========================";
        log.error "完整的MyPlugin，开始修改Class!";
        log.error "========================";


        //AppExtension就是build.gradle中android{...}这一块
//        def android = project.extensions.getByType(AppExtension);
//        android.registerTransform(new TransformTest(project))

//        注册一个Transform
//        project.android.registerTransform(new TransformTest(project));
        project.android.registerTransform(new ASMTransform(project));

        System.out.println("------------------结束----------------------->");


    }
}