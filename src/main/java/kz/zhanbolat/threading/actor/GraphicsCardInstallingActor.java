package kz.zhanbolat.threading.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kz.zhanbolat.threading.entity.GraphicsCard;

public class GraphicsCardInstallingActor extends AbstractActor {
    private LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(GraphicsCard.class, graphicsCard -> {
                    logger.info("Installed " + graphicsCard.printDetail());
                    getSender().tell(graphicsCard, getSelf());
                })
                .matchAny(o -> logger.error("Received not a graphics card from " + getSender() + ", the value " + o))
                .build();
    }
}
