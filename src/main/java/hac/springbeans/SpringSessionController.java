package hac.springbeans;
import hac.springbeans.beans.Label;
import hac.springbeans.beans.Messages;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SpringSessionController {

    // check the corresponding code in BeanConfiguration.java
    @Resource(name="autowiredFieldSingletonScope")
    private Label singletonLabel;

    // can also write : @Resource(name = "sessionBeanExample")
    @Autowired
    @Qualifier("sessionBeanExample")
    private Messages sessionMessages;

    // example of injection by setter
//    @Autowired
//    @Qualifier("sessionBeanExample")
//    public void setSessionMessages(Messages sessionMessages) {
//        this.sessionMessages = sessionMessages;
//    }

    // injection of component by ctor : match by name
    // check the corresponding code in Label.java
    @Resource(name="autowiredLabelDependency")
    private Label label;

    // injection by ctor : match by qualifier (variable name)
    // @Autowired ONLY does not work since there are 4 possibilities
    // check the corresponding code in BeanConfiguration.java
    @Resource(name="autowiredFieldApplicationScope")
    private Label applicationLabel;

    // injecting sesssion scope bean
    // check the corresponding code in BeanConfiguration.java
    //can also write @Resource(name="sessionScopeBeanExample")
    // @Qualifier is not mandatory if we have only one bean of that type
    @Autowired
    @Qualifier("sessionScopeBeanExample")
    private Label sessionScopeBeanExample;

    @GetMapping("/")
    public String process(Model model) {
        // this code belongs to Spring MVC with VIEW ENGINE (thymeleaf)
        model.addAttribute("sessionMessages", sessionMessages.getMessages());
        model.addAttribute("mylabel", label);
        model.addAttribute("singletonLabel", singletonLabel);
        model.addAttribute("applicationLabel", applicationLabel);
        return "session";
    }

    /* without SPRING injection  it would look like this:

    @PostMapping("/persistMessage")
    public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
        synchronized (SpringSessionController.class) { // global monitor
            List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
            if (messages == null) {
                messages = new ArrayList<>();
                request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
            }
            messages.add(msg);
        }
        request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
        return "redirect:/session";
    }

     */


    @PostMapping("/persistMessage")
    public String persistMessage(@RequestParam("msg") String msg) {

        sessionMessages.add(msg);
        return "redirect:/";
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}