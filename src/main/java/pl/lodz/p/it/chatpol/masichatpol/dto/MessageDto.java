package pl.lodz.p.it.chatpol.masichatpol.dto;



import com.ibm.watson.developer_cloud.assistant.v1.model.Context;

import java.util.Arrays;

public class MessageDto {
  private String message;
  private Context context;
  private String response;
  private String link;
  private String[] buttons;

  public MessageDto() {
  }

  public MessageDto(Context context, String response, String link, String[] buttons) {
    this.context = context;
    this.response = response;
    this.link = link;
    this.buttons = buttons;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Context getContext() {
    return context;
  }

  public void setContext(Context context) {
    this.context = context;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public String[] getButtons() {
    return buttons;
  }

  public void setButtons(String[] buttons) {
    this.buttons = buttons;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  @Override
  public String toString() {
    return "MessageDto{" +
        "message='" + message + '\'' +
        ", context=" + context +
        ", response='" + response + '\'' +
        ", link='" + link + '\'' +
        ", buttons=" + Arrays.toString(buttons) +
        '}';
  }
}