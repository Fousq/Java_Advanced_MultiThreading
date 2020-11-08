package kz.zhanbolat.threading.entity;

public class Cooler extends PCDetail {
    // indicate if cooler is plugged to motherboard
    private boolean isPowered;

    public Cooler(String producer, String name) {
        super(producer, name);
    }

    public boolean isPowered() {
        return isPowered;
    }

    public void setPowered(boolean powered) {
        isPowered = powered;
    }

    @Override
    public String printDetail() {
        return "Cooler " + producer + " " + name;
    }
}
