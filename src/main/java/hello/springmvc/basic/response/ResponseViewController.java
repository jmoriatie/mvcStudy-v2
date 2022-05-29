package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        return mav;
    }


    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello"; // Controller 라 View 를 찾음
    }

    @RequestMapping("/response-view-v3")
    public String responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    // Controller 의 경로가 같은 요청이 오면, void 를 반환할 시 해당 메서드를 실행한다
    // 좀 확실하게 잘 안보임 X
    @RequestMapping("/response/hello")
    public void responseViewV99(Model model) {
        model.addAttribute("data", "hello!");
    }
}
