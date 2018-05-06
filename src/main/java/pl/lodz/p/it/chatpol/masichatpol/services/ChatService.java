package pl.lodz.p.it.chatpol.masichatpol.services;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import org.springframework.stereotype.Service;
import pl.lodz.p.it.chatpol.masichatpol.dto.MessageDto;

@Service
public class ChatService {
  private static final String WORKSPACE_ID = "bee34e4f-2a83-45eb-920f-f992dad99f1f";
  private static final String USERNAME = "6dc27bc8-1869-48ba-b789-406673a9da48";
  private static final String PASSWORD = "fARYVOZkkpWw";
  private static final String VERSION = "2018-02-16";
  private static final String REGION = "https://gateway.watsonplatform.net/assistant/api";

  public MessageDto sendMessageToWatson(MessageDto message) {
    Assistant service = new Assistant(VERSION);
    service.setUsernameAndPassword(USERNAME, PASSWORD);
    service.setEndPoint(REGION);
    InputData input = new InputData.Builder(message.getMessage()).build();

    MessageOptions options = new MessageOptions.Builder(WORKSPACE_ID)
        .input(input)
        .context(message.getContext())
        .build();

    MessageResponse messageResponse = service.message(options).execute();

    return new MessageDto(messageResponse.getContext(), messageResponse.getOutput().getText());
  }
}