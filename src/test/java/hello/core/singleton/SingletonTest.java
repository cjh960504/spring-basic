package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회 : 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것은 확인해보자!
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 = hello.core.member.MemberServiceImpl@4988d8b8
        //memberService2 = hello.core.member.MemberServiceImpl@c0c2f8d =
        // => 순수한 자바로는 요청 시마다 서비스 객체를 생성했기때문에 당연한 결과!

        //memberService1 != memberSerivce2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2); //같지 않아야 함!
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        //객체를 생성하여 가져오는 것보다 참조를 통해 가져오는 것은 비용이 적다고 한다!
        SingleToneService instance1 = SingleToneService.getInstance();
        SingleToneService instance2 = SingleToneService.getInstance();

        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        Assertions.assertThat(instance1).isSameAs(instance2);
    }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        //1. 조회 : getBean을 호출하여 스프링 컨테이너에 등록되있는 Bean 객체를 가져오자
        MemberService memberService1 = applicationContext.getBean("memberService", MemberService.class);

        //1. 조회 : getBean을 호출하여 스프링 컨테이너에 등록되있는 Bean 객체를 가져오자(2)
        MemberService memberService2 = applicationContext.getBean("memberService", MemberService.class);

        //참조값이 같은 것을 확인해보자!
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 = memberSerivce2
        Assertions.assertThat(memberService1).isSameAs(memberService2); //같지 않아야 함!
    }
}
