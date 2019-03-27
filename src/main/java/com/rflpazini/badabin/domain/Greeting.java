package com.rflpazini.badabin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@RequiredArgsConstructor
public class Greeting {

  private final long id;
  private final String content;
  private final String apiName;
  private final String version;
}
