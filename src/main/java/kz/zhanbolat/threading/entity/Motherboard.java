package kz.zhanbolat.threading.entity;

import java.util.Objects;

public class Motherboard extends PCDetail {
    private String chipset;

    public Motherboard(String producer, String name, String chipset) {
        super(producer, name);
        this.chipset = chipset;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    @Override
    public String printDetail() {
        return toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Motherboard that = (Motherboard) o;
        return Objects.equals(chipset, that.chipset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), chipset);
    }

    @Override
    public String toString() {
        return "Motherboard{" +
                "chipset='" + chipset + '\'' +
                ", producer='" + producer + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
