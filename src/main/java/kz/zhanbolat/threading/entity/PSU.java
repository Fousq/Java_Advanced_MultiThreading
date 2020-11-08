package kz.zhanbolat.threading.entity;

import java.util.Objects;

public class PSU extends PCDetail {
    private boolean isPlugged;

    public PSU(String producer, String name) {
        super(producer, name);
    }

    public boolean isPlugged() {
        return isPlugged;
    }

    public void setPlugged(boolean plugged) {
        isPlugged = plugged;
    }

    @Override
    public String printDetail() {
        return "PSU " + producer + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PSU psu = (PSU) o;
        return isPlugged == psu.isPlugged;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isPlugged);
    }

    @Override
    public String toString() {
        return "PSU{" +
                "isPlugged=" + isPlugged +
                ", producer='" + producer + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
