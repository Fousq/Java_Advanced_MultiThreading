package kz.zhanbolat.threading.entity;

import java.util.Objects;

public class HardDrive extends PCDetail {
    // indicate if hard drive in a case
    private boolean isCased;

    public HardDrive(String producer, String name) {
        super(producer, name);
    }

    public boolean isCased() {
        return isCased;
    }

    public void setCased(boolean cased) {
        isCased = cased;
    }

    @Override
    public String printDetail() {
        return "Hard drive " + producer + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HardDrive hardDrive = (HardDrive) o;
        return isCased == hardDrive.isCased;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isCased);
    }

    @Override
    public String toString() {
        return "HardDrive{" +
                "isCased=" + isCased +
                ", producer='" + producer + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
