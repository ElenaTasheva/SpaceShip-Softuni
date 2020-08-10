package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {

    private final static double OXYGEN = 70;



    public Biologist(String name) {
        super(name, OXYGEN);
    }

    @Override
    public void breath() {
        super.decrease(5);
    }

}

