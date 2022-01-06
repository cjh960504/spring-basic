package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowriedTest {
    @Test
    void autowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        //  등록되지 않은 스프링 빈에 대해 의존 관계 주입 시킬 때, 없어도 NoSuchBean... 예외가 안터지도록 막는 법
        //  @Autowired(required = false)
        //  @Nullable Member member
        //  Optional<Member>  member : Optional<T>로 매개변수를 받는다. (Wrapper클래스에 감싸져있기 때문이다.)
        @Autowired(required = false)
        public void setNoBean1(Member member) {
            System.out.println("noBean1 = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member) {
            System.out.println("noBean2 = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member) {
            System.out.println("noBean3 = " + member);
        }
    }
}
