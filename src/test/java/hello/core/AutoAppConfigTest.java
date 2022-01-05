package hello.core;

import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class AutoAppConfigTest {
    @Test
    @DisplayName("@ComponentScan, @Component, @Autowired를 이용한 의존 객체 주입")
    void basicScan(){
        //given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        //when
        MemberService memberService = ac.getBean(MemberService.class);
        OrderService orderService = ac.getBean(OrderService.class);

        //then
        System.out.println("memberService.getMemberRepository() = " + memberService.getMemberRepository());
        System.out.println("orderService.getMemberRepository() = " + orderService.getMemberRepository());

        assertThat(memberService.getMemberRepository()).isEqualTo(orderService.getMemberRepository());
    }
}