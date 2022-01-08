package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor //final이 붙은 필드를 대상으로 생성자를 자동으로 만들어줌
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy rateDiscountPolicy; //@Autowired 특징으로 값은 타입 매칭 후, 필드명 또한 파라미터 이름을 통해 빈을 매칭한다.


//    @Autowired //의존관계 주입 : 생성자 주입 , 추가 구분자 @Qalifier 이용하여 같은 타입 여러 빈 구분하기
//    public OrderServiceImpl(MemberRepository memberRepository,  @Qualifier("fixDiscountPolicy") DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

 /*   @Autowired //의존관계 주입 : 일반 메서드 주입
    public void init(MemberRepository memberRepository, OrderService orderService) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        //단일책임 원칙이 잘 이루어져있기에 주문 서비스는 할인정책에 대해 구체적으로 알 필요가 없다. 변경되어도 영향을 받지 않는다.
        int disCountPrice = rateDiscountPolicy.disCount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, disCountPrice);
    }

    @Override
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
