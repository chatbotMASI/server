package pl.lodz.p.it.chatpol.masichatpol.services;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;

public class WatsonService {
    private static String workspaceId = "bee34e4f-2a83-45eb-920f-f992dad99f1f";
    private static String username = "6dc27bc8-1869-48ba-b789-406673a9da48";
    private static String password = "fARYVOZkkpWw";
    private static String version = "2018-02-16";
    private static String region = "https://gateway.watsonplatform.net/assistant/api";

    public static MessageResponse sendMessage(String message) {
        Assistant service = new Assistant(version);
        service.setUsernameAndPassword(username, password);
        service.setEndPoint(region);
        InputData input = new InputData.Builder(message).build();

        MessageOptions options = new MessageOptions.Builder(workspaceId)
                .input(input)
                .build();

        return service.message(options).execute();
    }
}