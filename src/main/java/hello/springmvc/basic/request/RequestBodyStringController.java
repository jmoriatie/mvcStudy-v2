package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // inputStream : 바이트코드기 때문에 항상 인코딩 방식을 알려줘야함 / 아닐시 default 로 실행

        log.info("messageBody={}", message);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException, ServletException {
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8); // inputStream : 바이트코드기 때문에 항상 인코딩 방식을 알려줘야함 / 아닐시 default 로 실행

        log.info("messageBody={}", message);

        responseWriter.write("ok");
    }

    // HttpEntity : header, body 편하게 조회함 (header 도 get으로 조회 가능)
    // 요청 파라미터 조회 기능과는 상관이 X -> get, form, json 이런거랑 관련이 없다는 뜻
    // 위의 세개 이외에는 그냥 이러한 HttpEntity 를 이용해서 꺼내야함
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity <String> httpEntity) throws IOException, ServletException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }

    // [참고] RequestEntity, ResponseEntity
//    @PostMapping("/request-body-string-v3")
//    public HttpEntity<String> requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException, ServletException {
//
//        String messageBody = httpEntity.getBody();
//        log.info("messageBody={}", messageBody);
//
//        return new ResponseEntity<>("ok", HttpStatus.CREATED); // 메시지와 상태코드를 넣을 수 있음
//    }

    // 요청 @RequestBody  : 받아가지고 바로 걍 꺼내줌
    // 응답 @ResponseBody : 문자열을 http body 에 딱 넣어줌
    // (중요!) 메시지 바디 내용을 바로 조회하는 기능은 @RequestParam, @ModelAttribute 랑은 전혀 관계가 없음
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody){
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
