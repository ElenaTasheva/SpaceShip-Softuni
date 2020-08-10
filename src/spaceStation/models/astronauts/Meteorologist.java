package spaceStation.models.astronauts;

public class Meteorologist extends BaseAstronaut{

    private final static double OXYGEN = 90;


    public Meteorologist(String name) {
        super(name, OXYGEN);
    }
}

