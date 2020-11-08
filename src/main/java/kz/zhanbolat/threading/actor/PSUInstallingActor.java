package kz.zhanbolat.threading.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kz.zhanbolat.threading.entity.PSU;

public class PSUInstallingActor extends AbstractActor {
    private LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PSU.class, psu -> {
                    logger.info("Installed " + psu.printDetail());
                    getSender().tell(psu, getSelf());
                })
                .matchAny(o -> logger.error("Received not a psu from " + getSender()))
                .build();
    }
}
