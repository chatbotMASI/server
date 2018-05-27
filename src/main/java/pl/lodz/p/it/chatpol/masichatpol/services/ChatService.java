package pl.lodz.p.it.chatpol.masichatpol.services;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.chatpol.masichatpol.collections.Dialog;
import pl.lodz.p.it.chatpol.masichatpol.collections.Log;
import pl.lodz.p.it.chatpol.masichatpol.dto.MessageDto;
import pl.lodz.p.it.chatpol.masichatpol.repositories.LogsRepository;

import java.util.Collection;

@Service
public class ChatService {
  private static final String WORKSPACE_ID = "bee34e4f-2a83-45eb-920f-f992dad99f1f";
  private static final String USERNAME = "6dc27bc8-1869-48ba-b789-406673a9da48";
  private static final String PASSWORD = "fARYVOZkkpWw";
  private static final String VERSION = "2018-02-16";
  private static final String REGION = "https://gateway.watsonplatform.net/assistant/api";

  private final LogsRepository repository;

  public ChatService(LogsRepository repository) {
    this.repository = repository;
  }

  private MessageDto parseButtonsAndLink(String message, Context context) {
    String[] buttons = null;
    String link = null;

    if (message.contains("[[")) {
      buttons = message.substring(message.indexOf("[") + 2, message.indexOf("]")).split(", ");
      message = message.substring(0, message.indexOf("["));
    }
    if (message.contains("{{")) {
      link = message.substring(message.indexOf("{") + 2, message.indexOf("}"));
      message = message.substring(0, message.indexOf("{"));
    }
    return new MessageDto(context, message, link, buttons);
  }

  public MessageDto sendMessageToWatson(MessageDto message, String remoteAddr) {
    Assistant service = new Assistant(VERSION);
    service.setUsernameAndPassword(USERNAME, PASSWORD);
    service.setEndPoint(REGION);

    MessageOptions options = new MessageOptions.Builder(WORKSPACE_ID)
        .input(message.getMessage() == null ? null : new InputData.Builder(message.getMessage()).build())
        .context(message.getContext())
        .build();

    MessageResponse messageResponse = service.message(options).execute();
    String strOutputText = String.join("", messageResponse.getOutput().getText());

    Log log = repository.findByConversationId(messageResponse.getContext().getConversationId()).orElse(new Log(messageResponse.getContext().getConversationId(), remoteAddr));
    log.getDialog().add(new Dialog(message.getMessage(), strOutputText));

    if (messageResponse.getOutput().getNodesVisited().stream().anyMatch(s -> s.contains("Anything else"))) {
      log.incrementFailureIndex();
    } else {
      log.incrementConversationLength();
    }

    repository.save(log);

    return parseButtonsAndLink(strOutputText, messageResponse.getContext());
  }

  public void rateUsability(String conversationId, Integer rate) {
    Log log = repository.findByConversationId(conversationId).get();
    log.setUsabilityScore(rate);
    repository.save(log);
  }

  public void rateEffectiveness(String conversationId, Integer rate) {
    Log log = repository.findByConversationId(conversationId).get();
    log.setEffectivenessScore(rate);
    repository.save(log);
  }

  public Collection<Log> getLogs() {
    return repository.findAll();
  }
}