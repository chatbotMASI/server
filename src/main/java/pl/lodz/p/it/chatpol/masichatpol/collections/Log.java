package pl.lodz.p.it.chatpol.masichatpol.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Document(collection = "logs")
public class Log {
  @Id
  private String id;

  @NotNull
  @NotEmpty
  private String conversationId;

  @NotNull
  @NotEmpty
  private String question;

  @NotNull
  @NotEmpty
  private String userReply;

  @NotNull
  @NotEmpty
  private String watsonReply;

  public Log() {
  }

  public Log(String conversationId, String question, String userReply, String watsonReply) {
    this.conversationId = conversationId;
    this.question = question;
    this.userReply = userReply;
    this.watsonReply = watsonReply;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getConversationId() {
    return conversationId;
  }

  public void setConversationId(String conversationId) {
    this.conversationId = conversationId;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getUserReply() {
    return userReply;
  }

  public void setUserReply(String userReply) {
    this.userReply = userReply;
  }

  public String getWatsonReply() {
    return watsonReply;
  }

  public void setWatsonReply(String watsonReply) {
    this.watsonReply = watsonReply;
  }
}
