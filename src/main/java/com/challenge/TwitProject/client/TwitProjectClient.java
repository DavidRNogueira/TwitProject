package com.challenge.TwitProject.client;

import com.challenge.TwitProject.dto.MessagesDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TwitProjectClient {
  private HttpURLConnection httpURLConnection;
  private ObjectReader objectReader;

  public TwitProjectClient () {

  }

  public TwitProjectClient (HttpURLConnection httpURLConnection) {
    this.httpURLConnection = httpURLConnection;
    this.objectReader = new ObjectMapper().readerFor(MessagesDto.class);
  }

  public MessagesDto getTweetsBySymbol (String symbol) {
    try {
      BufferedReader reader;
      String line;
      StringBuffer responseContent = new StringBuffer();

      URL url = new URL("https://api.stocktwits.com/api/2/streams/symbol/" +  symbol + ".json?limit=10");
      httpURLConnection = (HttpURLConnection) url.openConnection();
      httpURLConnection.setRequestMethod("GET");
      httpURLConnection.setConnectTimeout(5000);
      httpURLConnection.setReadTimeout(5000);
      httpURLConnection.connect();

      int status = httpURLConnection.getResponseCode();

      if (status > 299) {
        reader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));

        while ((line = reader.readLine()) != null){
          responseContent.append(line);
        }
        reader.close();
      } else {
        reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

        while ((line = reader.readLine()) != null){
          responseContent.append(line);
        }
        reader.close();
        String results = responseContent.toString();
        MessagesDto messages = objectReader.readValue(results);
        return messages;
      }


    }
    catch ( MalformedURLException e){
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      httpURLConnection.disconnect();
    }
    return null;
  }
}
