package kz.zhanbolat.threading.service;

import akka.actor.ActorRef;
import kz.zhanbolat.threading.entity.Cooler;
import kz.zhanbolat.threading.entity.PC;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static akka.pattern.Patterns.ask;

public class CoolerAssemblyService {
    private ActorRef router;

    public CoolerAssemblyService(ActorRef router) {
        this.router = router;
    }

    public void placeCooler(PC pc) {
        CompletableFuture<Object> future = ask(router, new Cooler("CoolMaster", "ES13"), Duration.ofMinutes(1)).toCompletableFuture();
        future.whenComplete((res, th) -> {
            if (Objects.isNull(res)) {
                throw new RuntimeException(th);
            }
            Cooler cooler = (Cooler) res;
            cooler.setPowered(true);
        });
        pc.setCooler((Cooler) future.join());
    }
}
