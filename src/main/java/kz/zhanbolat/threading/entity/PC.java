package kz.zhanbolat.threading.entity;

import java.util.Objects;

public class PC {
    private CPU cpu;
    private Cooler cooler;
    private GraphicsCard graphicsCard;
    private HardDrive hardDrive;
    private PSU psu;

    public PC() {

    }

    public CPU getCpu() {
        return cpu;
    }

    public void setCpu(CPU cpu) {
        this.cpu = cpu;
    }

    public Cooler getCooler() {
        return cooler;
    }

    public void setCooler(Cooler cooler) {
        this.cooler = cooler;
    }

    public GraphicsCard getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(GraphicsCard graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public HardDrive getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(HardDrive hardDrive) {
        this.hardDrive = hardDrive;
    }

    public PSU getPsu() {
        return psu;
    }

    public void setPsu(PSU psu) {
        this.psu = psu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PC pc = (PC) o;
        return Objects.equals(cpu, pc.cpu) &&
                Objects.equals(cooler, pc.cooler) &&
                Objects.equals(graphicsCard, pc.graphicsCard) &&
                Objects.equals(hardDrive, pc.hardDrive) &&
                Objects.equals(psu, pc.psu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpu, cooler, graphicsCard, hardDrive, psu);
    }

    @Override
    public String toString() {
        return "PC{" +
                "cpu=" + cpu +
                ", cooler=" + cooler +
                ", graphicsCard=" + graphicsCard +
                ", hardDrive=" + hardDrive +
                ", psu=" + psu +
                '}';
    }
}
