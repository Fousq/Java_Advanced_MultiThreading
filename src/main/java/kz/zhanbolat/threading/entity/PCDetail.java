package kz.zhanbolat.threading.entity;


import java.util.Objects;

public abstract class PCDetail {
    protected String producer;
    protected String name;

    protected PCDetail(String producer, String name) {
        this.producer = producer;
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String printDetail();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PCDetail pcDetail = (PCDetail) o;
        return Objects.equals(producer, pcDetail.producer) &&
                Objects.equals(name, pcDetail.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producer, name);
    }

    @Override
    public String toString() {
        return "PCDetail{" +
                "producer='" + producer + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
