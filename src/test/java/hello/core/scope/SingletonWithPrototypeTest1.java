package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

        /* ClineBean은 Scope가 singleton => 한번 생성되면 같은 객체를 사용
        => PrototypeBean을 새로 만드는 일은 ClientBean의 생성자를 호출하는 것 밖에 없음
        => 같은 PrototypeBean을 사용할 수 밖에 없다.*/
        /* 우리의 의도는 ClientBean.logic()을 호출할 때마다 PrototypeBean을 새로 생성해서 사용하고 싶은데..*/
        /* logic() 시 PrototypeBean을 새로 생성하도록 ApplicationContext를 사용할 수는 있지만, 좋은 코드는 못된다..(스프링에 의존적)*/
    }


    @Scope("singleton")
    static class ClientBean{

//        private PrototypeBean prototypeBean;

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider; //자바 표준에서 제공하는 DL 라이브러리(땡겨서 써야댐) But, get()만 제공함(단순)
        //private ObjectProvider<PrototypeBean> prototypeBeanProvider; //ObjectFactory 보다 기능이 추가된 ObjectProvider
        //private ObjectFactory<PrototypeBean> prototypeBeanObjectFactory;

//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }
        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy " + this);
        }
    }
}
