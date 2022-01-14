package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger; //MyLogger는 클라이언트의 request가 있어야 생성되어야하는데 LogDemoController 생성 시 주입되도록 만들었기 떄문에 오류가 난다!
   // private final ObjectProvider<MyLogger> myLoggerProvider; //요청마다 새로운 MyLogger 객체 생성

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURI = request.getRequestURL().toString();

        //class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$d775954d
        //의존성 주입 시 Spring 이 만든 라이브러리를 이용하여 가짜 프록시 객체로 대체
        //참고: @Configuration 사용한 객체에 대해 CGLIB을 볼 수 있었다. (싱글톤 레지스트리 관리)
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURI);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testid");

        return "OK";
    }
}
