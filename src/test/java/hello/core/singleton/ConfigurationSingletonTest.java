package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configuratioTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("MemberService -> MemberRepository = " + memberRepository1 );
        System.out.println("OrderService -> MemberRepository = " + memberRepository2 );
        System.out.println("MemberRepository = " + memberRepository );

        //로직상으론 new MemberRepository가 3번 실행되어 각각 다른 주소값을 가지고 있어야하는데 모두 같은 주소값을 출력한다.
        //스프링은 어떤 방식으로 만들었을까?
    }

    @Test
    void cofigurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class); //AppConfig@CGLIB는 AppConfig의 자식 타입이기에 가능

        System.out.println("bean = " + bean.getClass());
        //출력결과 bean = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$993824f4
        //순수한 클래스와는 다르게 출력되는 것을 확인가능 => 내가 만든 클래스가 아니다.
        // => 스프링이 바이트코드를 조작하는 라이브러리를 사용하여 AppConfig를 상속받아 사용하는 새로운 클래스를 만들었다고 볼 수 있다.
        // AppConfig appConfig = new AppConfig@CGLIB()  ?

    }
}
