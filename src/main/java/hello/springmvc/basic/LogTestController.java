package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(LogTestController.class); // 아래와 동일
//    private final Logger log = LoggerFactory.getLogger(getClass()); // @Slf4j => lombok 사용과 동일

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        // 반드시, {} 로 써야함 치환이기 때문에 쓸대없는 리소스를 낭비하지 않음 -> java 의 특징 때문
        log.trace("trace log={}", name); // 로컬에 주로
        log.debug("debug log={}", name); // 개발할 때 주로
        log.info("info log={}", name); // 운영 서버에 주로
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
