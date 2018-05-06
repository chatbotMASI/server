package pl.lodz.p.it.chatpol.masichatpol.controllers;

import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import org.springframework.web.bind.annotation.*;

import static pl.lodz.p.it.chatpol.masichatpol.services.WatsonService.sendMessage;


@RestController
public class WatsonController {

    @RequestMapping(value = "/watson", method = RequestMethod.POST)
    public @ResponseBody
    MessageResponse sayHello1(
            @RequestBody(required = false) String message) {
        return sendMessage(message);
    }

}
