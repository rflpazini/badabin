package com.rflpazini.badabin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class QueueStats {
  private int pending;
  private int completed;
}
