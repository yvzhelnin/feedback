package yz.feedback;

import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//REST контроллер, ожидающий данных из формы отправки записи
@RestController
public class SendController {
    /*получаем параметры, передаваемые с помощью AJAX из формы добавления
    нового сообщения*/
    @RequestMapping(value="send", method=RequestMethod.POST)
    public void sendFeedback(@RequestParam("secondName") String secondName,
                        @RequestParam("firstName") String firstName,
                        @RequestParam("patronymic") String patronymic,
                        @RequestParam("recipient") int recipientId,
                        @RequestParam("topic") String topic,
                        @RequestParam("message") String message){
        
        //создаём новую таблицу для сообщений (если старая отсутствует)
        DataBaseWorker.tableCreate();
        //пишем данные в таблицу
        DataBaseWorker.bindFeedback(secondName, firstName, patronymic, recipientId, topic, message);
    }
}
