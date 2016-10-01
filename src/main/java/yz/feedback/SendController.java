package yz.feedback;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SendController {
    
    @RequestMapping(value="send", method=RequestMethod.POST)
    public void sendFeedback(@RequestParam("secondName") String secondName,
                        @RequestParam("firstName") String firstName,
                        @RequestParam("patronymic") String patronymic,
                        @RequestParam("recipient") String recipient,
                        @RequestParam("topic") String topic,
                        @RequestParam("message") String message){
        
        DataBaseWorker.tableCreate();
        
        System.out.println(secondName);
        System.out.println(firstName);
        System.out.println(patronymic);
        System.out.println(recipient);
        System.out.println(topic);
        System.out.println(message);
    }
}
