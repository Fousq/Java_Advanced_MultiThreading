package kz.zhanbolat.threading.entity;

public class CPU extends PCDetail {
    // covered with termopasta
    private boolean isTermopastad;

    public CPU(String producer, String name) {
        super(producer, name);
    }

    public boolean isTermopastad() {
        return isTermopastad;
    }

    public void setTermopastad(boolean termopastad) {
        isTermopastad = termopastad;
    }

    @Override
    public String printDetail() {
        return "CPU " + producer + " " + name;
    }
}
