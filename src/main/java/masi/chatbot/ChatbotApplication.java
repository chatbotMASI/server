package masi.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChatbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatbotApplication.class, args);
		// after start "ctrl+shift+f10" go to http://localhost:8080/
		// you will see tmp front of the chat with messages
	}
}
