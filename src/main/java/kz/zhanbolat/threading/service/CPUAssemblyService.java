package kz.zhanbolat.threading.service;

import akka.actor.ActorRef;
import kz.zhanbolat.threading.entity.CPU;
import kz.zhanbolat.threading.entity.PC;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static akka.pattern.Patterns.ask;

public class CPUAssemblyService {
    private ActorRef router;

    public CPUAssemblyService(ActorRef router) {
        this.router = router;
    }

    public void placeCpu(PC pc) {
        CompletableFuture<Object> future = ask(router, new CPU("Intel", "core i7"), Duration.ofMinutes(1)).toCompletableFuture();
        future.whenComplete((res, th) -> {
            if (Objects.isNull(res)) {
                throw new RuntimeException(th);
            }
            CPU cpu = (CPU) res;
            cpu.setTermopastad(true);
        });
        pc.setCpu((CPU) future.join());
    }
}
