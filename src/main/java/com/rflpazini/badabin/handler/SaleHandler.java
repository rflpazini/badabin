package com.rflpazini.badabin.handler;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import com.rflpazini.badabin.service.QueueService;
import com.rflpazini.badabin.domain.QueueStats;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SaleHandler {

  @Value("${queue.name}")
  private String queueName;

  private final QueueService queueService;

  public Mono<ServerResponse> postMessage(ServerRequest request) {
    long quantity = Long.parseLong(request.queryParam("quantity").orElse("0"));
    String result = this.postToQueue(quantity);

    return ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(result));
  }

  public Mono<ServerResponse> getMessages(ServerRequest request) {
    var info = QueueStats.builder()
      .completed(this.queueService.completedJobs())
      .pending(this.queueService.pendingJobs(queueName))
      .build();

    return ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(info));
  }

  private String postToQueue(long quantity) {
    for (long i = 0; i < quantity; i++) {
      String id = UUID.randomUUID().toString();
      this.queueService.send(queueName, id);
    }

    return "success";
  }
}
