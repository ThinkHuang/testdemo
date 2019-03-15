package processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * 该插入式注解处理器是为了在编译期对类、方法和字段进行名称校验
 * @author huangyejun
 *
 */
@SupportedAnnotationTypes("*")//接受所有的注解类型
@SupportedSourceVersion(SourceVersion.RELEASE_8)//表示接受jdk1.8的java代码
public class NameCheckProcessor extends AbstractProcessor
{
    
    

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv)
    {
        return false;
    }
    
}
