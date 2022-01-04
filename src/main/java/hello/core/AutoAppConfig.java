package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member",  //=> 컴포넌트 스캔 시 시작위치를 지정
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
) //@Component 붙은 클래스를 찾아 자동으로 스프링 컨테이너에 등록
public class AutoAppConfig {
}
