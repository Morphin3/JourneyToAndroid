package com.taoism.javassist.transform

import com.android.build.api.transform.*
import com.android.build.gradle.internal.pipeline.TransformManager
import com.taoism.javassist.MyInjects
import org.gradle.api.Project
import org.apache.commons.io.FileUtils
import org.apache.commons.codec.digest.DigestUtils
/**
 * Date: 2018-05-10
 * Time: 13:18
 * Author: chenfei
 * -----------------------------
 * MISSION
 */
public class TransformTest extends Transform {

    private Project project;

    public TransformTest(Project project) {
        this.project = project;
    }

    //transform的名称
    //transformClassesWithMyClassTransformForDebug 运行时的名字
    //transformClassesWith + getName() + For + Debug或Release
    @Override
    public String getName() {
        return "TransformTest";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    @Override
    boolean isIncremental() {
        return false;
    }

//    @Override
//    void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
//        System.out.println("----------------进入transform了--------------")
//
//        //遍历input
////        inputs.each { TransformInput input ->
////            //遍历文件夹
////            input.directoryInputs.each { DirectoryInput directoryInput ->
////                //注入代码
////                MyInjects.inject(directoryInput.file.absolutePath, project)
////
////                // 获取output目录
////                def dest = outputProvider.getContentLocation(directoryInput.name,
////                        directoryInput.contentTypes,
////                        directoryInput.scopes,
////                        Format.DIRECTORY)   //这里写代码片
////
////                // 将input的目录复制到output指定目录
////                FileUtils.copyDirectory(directoryInput.file, dest);
////            }
////
////            ////遍历jar文件 对jar不操作，但是要输出到out路径
////            input.jarInputs.each { JarInput jarInput ->
////                // 重命名输出文件（同目录copyFile会冲突）
////                def jarName = jarInput.name
////                println("jar = " + jarInput.file.getAbsolutePath())
////                def md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
////                if (jarName.endsWith(".jar")) {
////                    jarName = jarName.substring(0, jarName.length() - 4)
////                }
////                def dest = outputProvider.getContentLocation(jarName + md5Name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
////                FileUtils.copyFile(jarInput.file, dest)
////            }
////        }//        }
//        System.out.println("--------------结束transform了----------------")
//    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
//        transformInvocation.getInputs()
//        transformInvocation.getReferencedInputs()
//        transformInvocation.getOutputProvider()
//        transformInvocation.isIncremental()

        System.out.println("----------------进入transform了--------------")

        //遍历input
        transformInvocation.getInputs().each { TransformInput input ->
            //遍历文件夹
            input.directoryInputs.each { DirectoryInput directoryInput ->
                //注入代码
                MyInjects.inject(directoryInput.file.absolutePath, project)

                // 获取output目录
                def dest = transformInvocation.getOutputProvider().getContentLocation(directoryInput.name,
                        directoryInput.contentTypes,
                        directoryInput.scopes,
                        Format.DIRECTORY)   //这里写代码片

                // 将input的目录复制到output指定目录
                FileUtils.copyDirectory(directoryInput.file, dest)
            }

            ////遍历jar文件 对jar不操作，但是要输出到out路径
            input.jarInputs.each { JarInput jarInput ->
                // 重命名输出文件（同目录copyFile会冲突）
                def jarName = jarInput.name
                println("jar = " + jarInput.file.getAbsolutePath())
                def md5Name = DigestUtils.md5Hex(jarInput.file.getAbsolutePath())
                if (jarName.endsWith(".jar")) {
                    jarName = jarName.substring(0, jarName.length() - 4)
                }
                def dest = transformInvocation.getOutputProvider().getContentLocation(jarName + md5Name, jarInput.contentTypes, jarInput.scopes, Format.JAR)
                FileUtils.copyFile(jarInput.file, dest)
            }
        }//        }
        System.out.println("--------------结束transform了----------------")
    }
}
