package model;

public class ExtractionPoint {
    private final RockType rockType;
    private int capacity;

    public ExtractionPoint(RockType rockType, int capacity) {
        this.rockType = rockType;
        this.capacity = capacity;
    }

    RockType getRockType() {
        return rockType;
    }

    public int getCapacity() {
        return capacity;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Loading point - " + rockType + " - " + capacity + " bags;";
    }
}
