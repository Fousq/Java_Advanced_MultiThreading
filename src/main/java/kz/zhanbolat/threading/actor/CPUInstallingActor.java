package kz.zhanbolat.threading.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kz.zhanbolat.threading.entity.CPU;

public class CPUInstallingActor extends AbstractActor {
    private final LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(CPU.class, msg -> {
                    logger.info("Installed " + msg.printDetail());
                    getSender().tell(msg, getSelf());
                })
                .matchAny(o -> logger.error("Received not a CPU from " + getSender()))
                .build();
    }
}
