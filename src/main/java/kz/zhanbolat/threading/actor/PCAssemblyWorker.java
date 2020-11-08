package kz.zhanbolat.threading.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.*;
import kz.zhanbolat.threading.entity.PCDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Global router for all pc assembly actors
 */
public class PCAssemblyWorker extends AbstractActor {
    private Router router;
    private LoggingAdapter logger;

    public PCAssemblyWorker(List<ActorRef> actorRefs, RoutingLogic routingLogic) {
        init(actorRefs, routingLogic);
    }

    public PCAssemblyWorker(List<ActorRef> actorRefs) {
        init(actorRefs, new RoundRobinRoutingLogic());
    }

    public PCAssemblyWorker() {
        List<ActorRef> actorRefs = new ArrayList<>();

        ActorRef cpuInstallingRoutee = getContext().actorOf(Props.create(CPUInstallingActor.class));
        ActorRef coolerInstallingRoutee = getContext().actorOf(Props.create(CoolerInstallingActor.class));
        ActorRef graphicsCardInstallingRoutee = getContext().actorOf(Props.create(GraphicsCardInstallingActor.class));
        ActorRef hardDriveInstallingRoutee = getContext().actorOf(Props.create(HardDriveInstallingActor.class));
        ActorRef psuInstallingRoutee = getContext().actorOf(Props.create(PSUInstallingActor.class));

        actorRefs.add(cpuInstallingRoutee);
        actorRefs.add(coolerInstallingRoutee);
        actorRefs.add(hardDriveInstallingRoutee);
        actorRefs.add(graphicsCardInstallingRoutee);
        actorRefs.add(psuInstallingRoutee);
        init(actorRefs, new RoundRobinRoutingLogic());
    }

    private void init(List<ActorRef> actorRefs, RoutingLogic routingLogic) {
        List<Routee> routees = new ArrayList<>();

        actorRefs.forEach(actorRef -> {
            getContext().watch(actorRef);
            routees.add(new ActorRefRoutee(actorRef));
        });

        router = new Router(routingLogic, routees);
        logger = Logging.getLogger(getContext().getSystem(), this);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(PCDetail.class, message -> {
                    logger.info("Got detail " + message.printDetail() + " from " + getSender());
                    router.route(message, getSender());
                })
                .build();
    }
}
