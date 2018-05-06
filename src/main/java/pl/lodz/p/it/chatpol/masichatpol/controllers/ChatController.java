package pl.lodz.p.it.chatpol.masichatpol.controllers;

import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.chatpol.masichatpol.dto.MessageDto;
import pl.lodz.p.it.chatpol.masichatpol.services.ChatService;


@RestController
public class ChatController {

  private final ChatService chatService;

  public ChatController(ChatService chatService) {
    this.chatService = chatService;
  }

  @CrossOrigin(origins = "http://localhost:3006")
  @RequestMapping(value = "/chat", method = RequestMethod.POST)
  public @ResponseBody
  MessageDto chat(@RequestBody(required = false) MessageDto message) {
    return chatService.sendMessageToWatson(message);
  }

}
