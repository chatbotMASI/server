package pl.lodz.p.it.chatpol.masichatpol.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Document(collection = "logs")
public class Log {
  @Id
  private String id;

  @NotNull
  @NotEmpty
  private String conversationId;

  @NotNull
  @NotEmpty
  private Collection<Dialog> dialog;

  private Integer conversationLength;

  private Integer failureIndex;

  private String clientIpAddress;

  private Integer effectivenessScore;

  private Integer usabilityScore;

  public Log() {
  }

  public Log(String conversationId, String ipAddress) {
    this.conversationId = conversationId;
    this.clientIpAddress = ipAddress;
    this.dialog = new ArrayList<>();
    this.conversationLength = 0;
    this.failureIndex = 0;
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

  public Collection<Dialog> getDialog() {
    return dialog;
  }

  public void setDialog(Collection<Dialog> dialog) {
    this.dialog = dialog;
  }

  /**
   * Average conversation length (ACL): the metrics evaluates the total number of
   * questions required to obtain a final result (a list of proposed items)
   * a) on each test run a total number of questions asked by a chatbot needs to be
   * stored and displayed
   * b) the average number of questions for all tests needs to be displayed; it is
   * proceeded with a title „AVERAGE”
   * c) The count of questions starts from, and includes the introductory question
   * (e.g. „Hello, how can I help you?”)
   * d) The count of questions excludes chatbot’s message when he doesn’t
   * understand the user’s input (e.g. „I do not understand, can you rephrase?”)
   */
  public Integer getConversationLength() {
    return conversationLength;
  }

  public void setConversationLength(Integer conversationLength) {
    this.conversationLength = conversationLength;
  }

  /**
   * Chatbot’s failure index (CFI): the metric evaluates how often the chatbot fails to
   * understand user's response
   * a) on each test run a total count of chatbot’s failure to interpret user's input (e.g.
   * „I do not understand, can you rephrase?”) needs to be stored and displayed
   * b) the total number of questions asked in a given test case needs to be
   * displayed next to CFI
   * c) the average score for all tests from a given test session needs to be
   * displayed; it is proceeded with a title „AVERAGE”
   */
  public Integer getFailureIndex() {
    return failureIndex;
  }

  public void setFailureIndex(Integer failureIndex) {
    this.failureIndex = failureIndex;
  }

  public String getClientIpAddress() {
    return clientIpAddress;
  }

  public void setClientIpAddress(String clientIpAddress) {
    this.clientIpAddress = clientIpAddress;
  }

  /**
   * Chatbot’s effectiveness score (CES): the metric explores user’s satisfaction level
   * with the products proposed
   * a) after the test run (once the results are displayed), chatbot asks the user to
   * evaluate its satisfaction with the returned list of products on a scale from 1
   * (lowest score) to 10 (highest score) and append the note to the file
   * b) he score is stored and displayed for every test run
   * c) the average score for all tests from a given test session needs to be
   * displayed; it is proceeded with a title „AVERAGE”
   */
  public Integer getEffectivenessScore() {
    return effectivenessScore;
  }

  public void setEffectivenessScore(Integer effectivenessScore) {
    this.effectivenessScore = effectivenessScore;
  }

  /**
   * Chatbot’s usability score (CUS): the metric explores user’s perception of chatbot’s
   * usability
   * a) after the test run (once the results are displayed), chatbot asks the user to
   * evaluate its usability on a scale from 1 (lowest score) to 10 (highest score)
   * b) the score is stored and displayed for every test run
   * c) the average score for all tests from a given test session needs to be
   * displayed; it is proceeded with a title „AVERAGE”
   */
  public Integer getUsabilityScore() {
    return usabilityScore;
  }

  public void setUsabilityScore(Integer usabilityScore) {
    this.usabilityScore = usabilityScore;
  }

  public void incrementFailureIndex() {
    this.failureIndex++;
  }

  public void incrementConversationLength() {
    this.conversationLength++;
  }
}
