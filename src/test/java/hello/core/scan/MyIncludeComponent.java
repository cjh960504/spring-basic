package hello.core.scan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //해당 어노테이션이 쓰일 위치
@Retention(RetentionPolicy.RUNTIME) //해당 어노테이션을 읽을 수 있는 범위?
@Documented // JavaDoc가 읽을 수 있는 어노테이션 지정
public @interface MyIncludeComponent {
}
