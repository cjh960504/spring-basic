package hello.core.xml;

import hello.core.member.MemberService;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    @Test
    void xmlAppContext() {
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        assertThat(memberService.getMemberRepository()).isEqualTo(orderService.getMemberRepository());
        System.out.println("memberSerivce.getMemberRepository = " + memberService.getMemberRepository());
        System.out.println("orderSerivce.getMemberRepository = " + orderService.getMemberRepository());
        //스프링 컨테이너에서 BeanDefinition의 Scope 기본값을 싱글톤을 가지고 있어서 서로 new하여 MemberRepository를 생성해도 같은 객체를 사용한다.
    }
}
