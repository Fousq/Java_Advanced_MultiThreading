package kz.zhanbolat.threading.service;

import akka.actor.ActorRef;
import kz.zhanbolat.threading.entity.*;

import static akka.pattern.Patterns.ask;

public class PCAssemblyService {
    private CPUAssemblyService cpuAssemblyService;
    private CoolerAssemblyService coolerAssemblyService;
    private HardDriveAssemblyService hardDriveAssemblyService;
    private GraphicsCardAssemblyService graphicsCardAssemblyService;
    private PSUAssemblyService psuAssemblyService;

    public PCAssemblyService(ActorRef router) {
        cpuAssemblyService = new CPUAssemblyService(router);
        coolerAssemblyService = new CoolerAssemblyService(router);
        hardDriveAssemblyService = new HardDriveAssemblyService(router);
        graphicsCardAssemblyService = new GraphicsCardAssemblyService(router);
        psuAssemblyService = new PSUAssemblyService(router);
    }

    public PC assemble(Motherboard motherboard) {
        PC pc = new PC();
        cpuAssemblyService.placeCpu(pc);
        coolerAssemblyService.placeCooler(pc);
        hardDriveAssemblyService.placeHardDrive(pc);
        graphicsCardAssemblyService.placeGraphicsCard(pc);
        psuAssemblyService.placePSU(pc);
        return pc;
    }
}
