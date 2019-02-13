package com.rflpazini.badabin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

public class Greeting {

  private final long id;
  private final String content;
  private final String apiName;
  private final String version;

  public Greeting(long id, String content, String apiName, String version) {
    this.id = id;
    this.content = content;
    this.apiName = apiName;
    this.version = version;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

  public String getApiName() {
    return apiName;
  }

  public String getVersion() {
    return version;
  }
}
