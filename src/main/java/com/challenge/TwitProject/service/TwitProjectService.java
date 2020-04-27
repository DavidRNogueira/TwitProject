package com.challenge.TwitProject.service;

import com.challenge.TwitProject.client.TwitProjectClient;
import com.challenge.TwitProject.dto.MessagesDto;
import com.challenge.TwitProject.dto.TweetDto;
import org.springframework.stereotype.Service;

@Service
public class TwitProjectService {

  private TwitProjectClient twitProjectClient;

  public TwitProjectService ()  {

  }

  public TwitProjectService(TwitProjectClient twitProjectClient){
    this.twitProjectClient = twitProjectClient;
  }

  public MessagesDto getTweetsBySymbol(String symbol){
      MessagesDto messagesDto = twitProjectClient.getTweetsBySymbol(symbol);
      for (TweetDto tweetDto : messagesDto.getMessages()){
          tweetDto.setSymbol(symbol);
      }
      return messagesDto;
  }
}
