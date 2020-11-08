package kz.zhanbolat.threading.entity;

import java.util.Objects;

public class GraphicsCard extends PCDetail {
    private boolean isPlaced;

    public GraphicsCard(String producer, String name) {
        super(producer, name);
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    @Override
    public String printDetail() {
        return "Graphics card " + producer + " " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GraphicsCard that = (GraphicsCard) o;
        return isPlaced == that.isPlaced;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isPlaced);
    }

    @Override
    public String toString() {
        return "GraphicsCard{" +
                "isPlaced=" + isPlaced +
                ", producer='" + producer + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
