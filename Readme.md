> # Spring MVC 학습2

#### 도구: spring boot, jsp, timeleaf / IntelliJ
___
#### [프로젝트 설명]
* 스프링 mvc 학습2
* 서버사이드 랜더링 new 기술 학습(timeleaf, jsp 포함)
___
> 학습내용
##### logger 사용
* Slf4j(interface) 를 활용한 logback(실제 로그 구현체) 사용
  - @Slf4j : 어노테이션 방법 
  - Logger log = LoggerFactory.getLogger(getClass()); : new 방법
##### @RequestParam, @ModelAttribute
* 요청파라미터를 조회하는 기능 (둘 다 생략 가능, 그럼 어떻게 식별?)
* @RequestParam : String, int, Integer 등 단순 자료형
* @ModelAttribute : 나머지 But! argument resolver 로 지정해둔 타입은 X (ex)HttpServeletRequest << 예약됨
##### @RequestBody, @ResponseBody(생략 불가능, 생략시 위 코드로 동작함)
* @RequestBody: HTTP 메세지 body 를 조회하는 기능
* @ResponseBody : HTTP body 에 직접 담아 전달하는 기능(View 사용 X)
  



