package kz.zhanbolat.threading.actor;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import kz.zhanbolat.threading.entity.HardDrive;

public class HardDriveInstallingActor extends AbstractActor {
    private LoggingAdapter logger = Logging.getLogger(getContext().getSystem(), this);

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(HardDrive.class, hardDrive -> {
                    logger.info("Installed " + hardDrive.printDetail());
                    getSender().tell(hardDrive, getSelf());
                })
                .matchAny(o -> logger.error("Received not a hard drive from " + getSender()))
                .build();
    }
}
