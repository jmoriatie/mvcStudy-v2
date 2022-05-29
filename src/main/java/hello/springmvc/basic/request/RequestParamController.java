package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    // request.getParameter -> @RequestParam("username")
    @ResponseBody // String 이 http 메세지에 박힘
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    // @RequestParam("username") -> @RequestParam : (조건)변수명 == 파라미터명
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // @RequestParam -> 이것조차도 삭제 : : (조건)변수명 == 파라미터명, String, int Integer 등 단순타입
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // @RequestParam(required = true) : 파라미터값이 안들어오면 오류남(default)
    // @RequestParam(required = false) : 안들어오면 null 로 입력
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username, // username 은 빈문자("") 로 들어올 때 성공함, 조심! 예외처리 필수
            @RequestParam(required = false) Integer age) { // rapper class 사용 : int 는 기본형이기 때문에 null 이 들어가면 오류남

        log.info("username={}, age={}", username, username);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) { // default value 쓰면, int로도 사용 가능 (문자열에서 파싱되는 것)
            // ㄴ 이렇게 되면 사실상 required 가 필요 딱히 없음

        log.info("username={}, age={}", username, username);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap){

        // 애매하게 하나의 키 값에, 여러 밸류가 들어올 경우 MultiValueMap<> 사용! -> 거의 없는 경우
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age")) ;
        return "ok";
    }

    // ModelAndView--------------------------------

    // @ModelAttribute : 객체 프로퍼티(set, get) 찾음
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData hellodata){ // @RequestParam String username, @RequestParam int age
//        HelloData hellodata = new HelloData();
//        hellodata.setUsername(username);
//        hellodata.setAge(age);

        log.info("username={}, age={}", hellodata.getUsername(), hellodata.getAge());
        log.info("hellodate={}", hellodata); // HelloData 에 @Data 에는 toString 이 포함되어 있어서, 자체 조회시에도 나옴

        return "ok";
    }
    // 데이터를 넣는 걸 binding : 숫자->문자 넣거나 하면 binding 오류남(BindingException)
    // @ModelAttribute("뷰에 넘길 이름") => 데이터를 담아 그대로 뷰에 넘겨줄 이름을 지정할 수 있음

    // @ModelAttribute 생략 가능
    // * 둘 다 생략 가능한데 어떻게 식별?
    // ㄴ @Requestparam : int, String Integer 같은 단순 타입 처리
    // ㄴ @ModelAttribute : 나머지 -> But! argument resolver 로 지정해둔 타입은 X (ex)HttpServeletRequest << 예약됨
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData hellodata){
        log.info("username={}, age={}", hellodata.getUsername(), hellodata.getAge());
        log.info("hellodate={}", hellodata);

        return "ok";
    }
}
