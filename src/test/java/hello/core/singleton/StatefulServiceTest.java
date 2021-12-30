package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : A 사용자 10000원 주문
        int test1Price = statefulService1.order("test1", 10000);
        //ThreadB : B 사용자 20000원 주문
        int test2Price = statefulService2.order("test2", 20000);

        //ThreadA : 사용자 A 주문 금액 조회
//        int price1 = statefulService1.getPrice();
        //ThreadB : 사용자 B 주문 금액 조회
//        int price2 = statefulService2.getPrice();
        System.out.println("test1Price1 = " + test1Price);
        //같은 객체의 멤버 변수를 공유하기 때문에 같은 변수의 값을 반환하게 된다. (Stateful)
//        Assertions.assertThat(price1).isEqualTo(price2);
    }

    @Configuration
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}