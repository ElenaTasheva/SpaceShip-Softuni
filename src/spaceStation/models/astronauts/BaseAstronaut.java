package spaceStation.models.astronauts;

import spaceStation.models.bags.Backpack;
import spaceStation.models.bags.Bag;

import java.util.stream.Collectors;

public class BaseAstronaut implements Astronaut {

    private String name;
    private double oxygen;
    private Bag bag;
    private static final int decreaseAmount = 10;

    public BaseAstronaut(String name, double oxygen) {
        setName(name);
        setOxygen(oxygen);
        this.bag = new Backpack();
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new NullPointerException("Astronaut name cannot be null or empty.");
        }
        this.name = name;
    }

    private void setOxygen(double oxygen) {
        if(oxygen < 0){
            throw new IllegalArgumentException("Cannot create Astronaut with negative oxygen!");
        }
        this.oxygen = oxygen;
    }

    private void setBag(Bag bag) {
        if(bag.getClass().isInstance(Bag.class)){
            this.bag = bag;
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getOxygen() {
        return this.oxygen;
    }

    @Override
    public boolean canBreath() {
        return this.oxygen > 0;
    }

    @Override
    public Bag getBag() {
        return bag;
    }

    @Override
    public void breath() {
        decrease(decreaseAmount);
    }

    protected void decrease(int decreaseAmount){
        if(this.oxygen - decreaseAmount >= 0){
            this.oxygen = this.oxygen -  decreaseAmount;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(System.lineSeparator())
                .append("Oxygen: ").append(String.format("%.0f", getOxygen()))
                .append(System.lineSeparator())
                .append("Bag items: ");
        if(getBag().getItems().size() == 0){
            sb.append("none");
        }
        else {
            String bag = String.join(", ", getBag().getItems());
            sb.append(bag);
        }
        return sb.toString();
    }
}

