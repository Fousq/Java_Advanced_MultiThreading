package kz.zhanbolat.threading.service;

import akka.actor.ActorRef;
import kz.zhanbolat.threading.entity.PC;
import kz.zhanbolat.threading.entity.PSU;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static akka.pattern.Patterns.ask;

public class PSUAssemblyService {
    private ActorRef router;

    public PSUAssemblyService(ActorRef router) {
        this.router = router;
    }

    public void placePSU(PC pc) {
        CompletableFuture<Object> future = ask(router, new PSU("Corsair", "GX600"), Duration.ofMinutes(1)).toCompletableFuture();
        future.whenComplete((res, th) -> {
           if (Objects.isNull(res)) {
               throw new RuntimeException(th);
           }
           PSU psu = (PSU) res;
           psu.setPlugged(true);
        });
        pc.setPsu((PSU) future.join());
    }
}
