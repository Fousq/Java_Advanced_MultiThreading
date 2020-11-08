package kz.zhanbolat.threading.service;

import akka.actor.ActorRef;
import kz.zhanbolat.threading.entity.HardDrive;
import kz.zhanbolat.threading.entity.PC;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import static akka.pattern.Patterns.ask;

public class HardDriveAssemblyService {
    private ActorRef router;

    public HardDriveAssemblyService(ActorRef router) {
        this.router = router;
    }

    public void placeHardDrive(PC pc) {
        CompletableFuture<Object> future = ask(router, new HardDrive("WD", "Black 1TB"), Duration.ofMinutes(1)).toCompletableFuture();
        future.whenComplete((res, th) -> {
            if (Objects.isNull(res)) {
                throw new RuntimeException(th);
            }
            HardDrive hardDrive = (HardDrive) res;
            hardDrive.setCased(true);
        });
        pc.setHardDrive((HardDrive) future.join());
    }
}
