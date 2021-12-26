package hello.core.member;

import hello.core.AppConfig;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void 생성주입(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void 회원조회(){
        //given
        Member memberA = new Member(1L, "memberA", Grade.BASIC);
        Member memberB = new Member(2L, "memberB", Grade.BASIC);

        //when
        memberService.join(memberA);
        memberService.join(memberB);

        Member findMemberA = memberService.findMember(1L);
        Member findMemberB= memberService.findMember(2L);
        //Member findMemberC= memberService.findMember(3L);

        //then
        Assertions.assertThat(findMemberA).isEqualTo(memberA);
        Assertions.assertThat(findMemberB).isEqualTo(memberB);
        //Assertions.assertThat(findMemberC).isEqualTo(null);
    }
}
