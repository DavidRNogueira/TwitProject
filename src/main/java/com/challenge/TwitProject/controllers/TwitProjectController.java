package com.challenge.TwitProject.controllers;

import com.challenge.TwitProject.dto.MessagesDto;
import com.challenge.TwitProject.service.TwitProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwitProjectController {

  private TwitProjectService twitProjectService;

  public TwitProjectController(TwitProjectService twitProjectService){
    this.twitProjectService = twitProjectService;
  }

  @GetMapping("/get-tweets-by-symbol")
  public MessagesDto getTweetsBySymbol (@RequestParam(name="symbol") String symbol){
    return twitProjectService.getTweetsBySymbol(symbol);
  }
}
