package pl.lodz.p.it.chatpol.masichatpol.collections;

public class Dialog {
  private String userMessage;
  private String watsonReply;

  public Dialog() {
  }

  public Dialog(String userMessage, String watsonReply) {
    this.userMessage = userMessage;
    this.watsonReply = watsonReply;
  }

  public String getUserMessage() {
    return userMessage;
  }

  public void setUserMessage(String userMessage) {
    this.userMessage = userMessage;
  }

  public String getWatsonReply() {
    return watsonReply;
  }

  public void setWatsonReply(String watsonReply) {
    this.watsonReply = watsonReply;
  }
}