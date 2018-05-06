package pl.lodz.p.it.chatpol.masichatpol.controllers;

import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.chatpol.masichatpol.services.ChatService;


@RestController
public class ChatController {

  private final ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  @RequestMapping(value = "/watson", method = RequestMethod.POST)
  public @ResponseBody
  MessageResponse sayHello(@RequestBody(required = false) String message) {
    return chatService.sendMessageToWatson(message);
  }

}
