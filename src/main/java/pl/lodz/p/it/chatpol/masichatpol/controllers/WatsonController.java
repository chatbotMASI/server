package pl.lodz.p.it.chatpol.masichatpol.controllers;

import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import org.springframework.web.bind.annotation.*;


@RestController
public class WatsonController {
    private String workspaceId = "bee34e4f-2a83-45eb-920f-f992dad99f1f";
    private String username = "6dc27bc8-1869-48ba-b789-406673a9da48";
    private String password = "fARYVOZkkpWw";
    private String version = "2018-02-16";
    private String region = "https://gateway.watsonplatform.net/assistant/api";


    @RequestMapping(value = "/watson", method = RequestMethod.POST)
    public @ResponseBody
    MessageResponse sayHello1(
            @RequestBody(required = false) String message) {
        return sendMessage(message);
    }


    private MessageResponse sendMessage(String message) {
        Assistant service = new Assistant(this.version);
        service.setUsernameAndPassword(this.username, this.password);
        service.setEndPoint(this.region);
        InputData input = new InputData.Builder(message).build();

        MessageOptions options = new MessageOptions.Builder(this.workspaceId)
                .input(input)
                .build();

        return service.message(options).execute();
    }
}
