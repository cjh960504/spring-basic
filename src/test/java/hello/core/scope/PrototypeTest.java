package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {
    @Test
    void prototypeBeanFind() {
        //PrototypeBean에 @Component가 없어도 동작?
        //AnnotationConfigApplicationContext 의 인자로 넘겨주면 알아서 인식
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        //클라이언트에서 프로토타입의 스코프를 가진 빈을 요청할 때마다 스프링 컨테이너에서 새로운 인스턴스를 만들어줌. (스프링 컨테이너에서 관리하지 않으므로)
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        
        //종료메서드 호출이 필요하면 클라이언트가 요청해야함
        prototypeBean1.destroy();
        prototypeBean2.destroy();

        ac.close();
    }

    @Scope("prototype")
    public static class PrototypeBean{
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }
        
        //프로토타입의 스코프를 가진 빈은 의존관계 주입 외에는 스프링 컨테이너에서 관리하지 않는다. (클라이언트에게 관리 위임)
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
