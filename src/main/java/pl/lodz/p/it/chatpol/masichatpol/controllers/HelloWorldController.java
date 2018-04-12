package pl.lodz.p.it.chatpol.masichatpol.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @GetMapping(value = "/hello")
  public String hello() {
    return "Hello World.";
  }
}
