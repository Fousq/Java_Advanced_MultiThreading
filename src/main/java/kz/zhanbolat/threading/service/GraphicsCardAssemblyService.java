package kz.zhanbolat.threading.service;

import akka.actor.ActorRef;
import kz.zhanbolat.threading.entity.GraphicsCard;
import kz.zhanbolat.threading.entity.PC;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static akka.pattern.Patterns.ask;

public class GraphicsCardAssemblyService {
    private ActorRef router;

    public GraphicsCardAssemblyService(ActorRef router) {
        this.router = router;
    }

    public void placeGraphicsCard(PC pc) {
        CompletableFuture<Object> future = ask(router, new GraphicsCard("NVIDIA", "RTX3080"), Duration.ofMinutes(1)).toCompletableFuture();
        future.whenComplete((res, th) -> {
           if (Objects.isNull(res)) {
               throw new RuntimeException(th);
           }
           GraphicsCard graphicsCard = (GraphicsCard) res;
           graphicsCard.setPlaced(true);
        });
        pc.setGraphicsCard((GraphicsCard) future.join());
    }
}
