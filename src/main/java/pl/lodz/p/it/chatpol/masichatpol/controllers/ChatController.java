package pl.lodz.p.it.chatpol.masichatpol.controllers;

import com.itextpdf.text.DocumentException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.it.chatpol.masichatpol.collections.Log;
import pl.lodz.p.it.chatpol.masichatpol.dto.MessageDto;
import pl.lodz.p.it.chatpol.masichatpol.services.ChatService;
import pl.lodz.p.it.chatpol.masichatpol.utils.PdfGeneratorUtil;

import java.io.IOException;
import java.util.Collection;


@RestController
public class ChatController {

  private final ChatService chatService;
  private final PdfGeneratorUtil pdfGenerator;

  public ChatController(ChatService chatService, PdfGeneratorUtil pdfGenerator) {
    this.chatService = chatService;
    this.pdfGenerator = pdfGenerator;
  }

  @CrossOrigin(origins = "*")
  @PostMapping(value = "/chat")
  public @ResponseBody
  MessageDto chat(@RequestBody(required = false) MessageDto message) {
    return chatService.sendMessageToWatson(message);
  }

  @CrossOrigin(origins = "*")
  @PostMapping(value = "/usability-rate")
  public void rateUsability(@RequestParam String conversationId, @RequestBody Integer rate) {
    chatService.rateUsability(conversationId, rate);
  }

  @CrossOrigin(origins = "*")
  @PostMapping(value = "/effectiveness-rate")
  public void rateEffectiveness(@RequestParam String conversationId, @RequestBody Integer rate) {
    chatService.rateEffectiveness(conversationId, rate);
  }

  @RequestMapping(value = "generate-report", method = RequestMethod.GET)
  public @ResponseBody
  ResponseEntity generatePdf() {
    try {
      Collection<Log> logs = this.chatService.getLogs();
      byte[] out = this.pdfGenerator.generate(logs);

      HttpHeaders hHeaders = new HttpHeaders();
      hHeaders.add("Content-Type", "application/pdf");
      hHeaders.add("content-disposition", "attachment; filename=chatPOL_conversation_report.pdf");
      return new ResponseEntity(out, hHeaders, HttpStatus.OK);
    } catch (IOException | DocumentException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("INTERNAL_SERVER_ERROR");
    }
  }
}
