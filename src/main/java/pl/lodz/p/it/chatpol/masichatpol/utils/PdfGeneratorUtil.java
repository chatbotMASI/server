package pl.lodz.p.it.chatpol.masichatpol.utils;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import pl.lodz.p.it.chatpol.masichatpol.collections.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

@Component
public class PdfGeneratorUtil {
  private static final String TEMPLATE_NAME = "report-template";

  private final TemplateEngine templateEngine;

  @Autowired
  public PdfGeneratorUtil(TemplateEngine templateEngine) {
    this.templateEngine = templateEngine;
  }

  public byte[] generate(Collection<Log> logs) throws DocumentException, IOException {
    byte[] out;
    ByteArrayOutputStream os = new ByteArrayOutputStream();
    Context ctx = new Context();
    ctx.setVariable("logs", logs);
    ctx.setVariable("acl", String.format("%.2f", logs.stream().mapToInt(Log::getConversationLength).average().orElse(0)));
    ctx.setVariable("cfi", String.format("%.2f", logs.stream().mapToInt(Log::getFailureIndex).average().orElse(0)));
    ctx.setVariable("cus", String.format("%.2f", logs.stream().filter(log -> log.getUsabilityScore() != null).mapToInt(Log::getUsabilityScore).average().orElse(0)));
    ctx.setVariable("ces", String.format("%.2f", logs.stream().filter(log -> log.getEffectivenessScore() != null).mapToInt(Log::getEffectivenessScore).average().orElse(0)));
    String processedHtml = templateEngine.process(TEMPLATE_NAME, ctx);
    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(processedHtml);
    renderer.layout();
    renderer.createPDF(os);
    out = os.toByteArray();
    os.close();
    return out;
  }
}