package pl.lodz.p.it.chatpol.masichatpol.dto;



import com.ibm.watson.developer_cloud.assistant.v1.model.Context;

public class MessageDto {
  private String message;
  private Context context;
  private String response;

  public MessageDto() {
  }

  public MessageDto(Context context, String response) {
    this.context = context;
    this.response = response;
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

  @Override
  public String toString() {
    return "MessageDto{" +
        "message='" + message + '\'' +
        ", context=" + context +
        ", response=" + response +
        '}';
  }
}