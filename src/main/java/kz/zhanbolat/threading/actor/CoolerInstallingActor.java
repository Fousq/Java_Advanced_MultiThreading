package kz.zhanbolat.threading.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kz.zhanbolat.threading.entity.Cooler;

public class CoolerInstallingActor extends AbstractActor {
    private LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Cooler.class, cooler -> {
                    logger.info("Installed " + cooler.printDetail());
                    getSender().tell(cooler, getSelf());
                })
                .matchAny(o -> logger.error("Received not a cooler from " + getSender()))
                .build();
    }
}
