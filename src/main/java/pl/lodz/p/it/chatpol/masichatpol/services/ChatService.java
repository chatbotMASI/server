package pl.lodz.p.it.chatpol.masichatpol.services;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.chatpol.masichatpol.collections.Log;
import pl.lodz.p.it.chatpol.masichatpol.dto.MessageDto;
import pl.lodz.p.it.chatpol.masichatpol.repositories.LogsRepository;

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

  public MessageDto sendMessageToWatson(MessageDto message) {
    Assistant service = new Assistant(VERSION);
    service.setUsernameAndPassword(USERNAME, PASSWORD);
    service.setEndPoint(REGION);

    MessageOptions options = new MessageOptions.Builder(WORKSPACE_ID)
        .input(message.getMessage() == null ? null : new InputData.Builder(message.getMessage()).build())
        .context(message.getContext())
        .build();

    MessageResponse messageResponse = service.message(options).execute();

    String strOutputText = String.join("", messageResponse.getOutput().getText());
    String[] buttons = null;
    String link = null;

    if (strOutputText.contains("[[")) {
      buttons = strOutputText.substring(strOutputText.indexOf("[") + 2, strOutputText.indexOf("]") - 1).split(",");
      strOutputText = strOutputText.substring(0, strOutputText.indexOf("["));
    }

    if (strOutputText.contains("{{")) {
      link = strOutputText.substring(strOutputText.indexOf("{") + 2, strOutputText.indexOf("}") -1);
      strOutputText = strOutputText.substring(0, strOutputText.indexOf("{"));
    }

    repository.save(new Log(messageResponse.getContext().getConversationId(), message.getMessage(), strOutputText));

    return new MessageDto(messageResponse.getContext(), strOutputText, link, buttons);
  }
}