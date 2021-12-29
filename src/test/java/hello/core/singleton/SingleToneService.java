package hello.core.singleton;

//싱글톤 테스트를 위한 임시 서비스
public class SingleToneService {
    private static final SingleToneService singleToneService = new SingleToneService();

    public static SingleToneService getInstance(){
        return singleToneService;
    }

    //생성자를 private로 하여 누구도 이 객체를 생성하여 사용하지 못하도록 막는다.
    private SingleToneService(){

    }
    
    public void logic(){
        System.out.println("싱글톤 객체 클릭 호출");
    }
}
