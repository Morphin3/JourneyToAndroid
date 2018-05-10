package com.taoism.plugin

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
        System.out.println("------------------结束----------------------->");
    }
}