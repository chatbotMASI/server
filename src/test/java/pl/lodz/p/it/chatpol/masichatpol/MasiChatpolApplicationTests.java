package pl.lodz.p.it.chatpol.masichatpol;

import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.lodz.p.it.chatpol.masichatpol.dto.MessageDto;
import pl.lodz.p.it.chatpol.masichatpol.services.ChatService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MasiChatpolApplicationTests {

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  private ChatService chatService;

  private MockMvc mockMvc;

  @Before
  public void setup() throws Exception {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }


  @Test
  public void contextLoads() throws Exception {
    this.mockMvc.perform(get("http://localhost:8080/hello")).andDo(print())
        .andDo(print()).andExpect(status().isOk())
        .andExpect(content().string("Hello World."));
  }

  @Test
  public void whenMessageWithButtons_thenReturnResponseDtoWithButtonsList() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    final String message = "What exactly are you interested in? [[Steel-string acoustics, Beginner kits, resonators ]]";
    final String[] expectedButtonsArray = {"Steel-string acoustics", "Beginner kits", "resonators "};

    Method method = chatService.getClass().getDeclaredMethod("parseButtonsAndLink", String.class, Context.class);
    method.setAccessible(true);

    Assert.assertArrayEquals(expectedButtonsArray, ((MessageDto) method.invoke(chatService, message, null)).getButtons());
  }

  @Test
  public void whenMessageWithLink_thenReturnResponseDtoWithLink() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    final String message = "Great! Here are some resonators you might like!  {{https://www.amazon.com/s/ref=lp_11971251_nr_n_1?fst=as%3Aoff&rh=n%3A11091801%2Cn%3A%2111965861%2Cn%3A11971241%2Cn%3A11971251%2Cn%3A11971301&bbn=11971251&ie=UTF8&qid=1526235353&rnid=11971251}}";
    final String expectedLink = "https://www.amazon.com/s/ref=lp_11971251_nr_n_1?fst=as%3Aoff&rh=n%3A11091801%2Cn%3A%2111965861%2Cn%3A11971241%2Cn%3A11971251%2Cn%3A11971301&bbn=11971251&ie=UTF8&qid=1526235353&rnid=11971251";

    Method method = chatService.getClass().getDeclaredMethod("parseButtonsAndLink", String.class, Context.class);
    method.setAccessible(true);

    Assert.assertEquals(null, expectedLink, ((MessageDto) method.invoke(chatService, message, null)).getLink());
  }
}
