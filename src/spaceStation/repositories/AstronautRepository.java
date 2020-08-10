package spaceStation.repositories;

import spaceStation.models.astronauts.Astronaut;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AstronautRepository implements Repository<Astronaut>{

    private final List<Astronaut> astronauts = new ArrayList<>();


    @Override
    public Collection<Astronaut> getModels() {
        return Collections.unmodifiableCollection(astronauts);
    }

    @Override
    public void add(Astronaut astronaut) {
        astronauts.add(astronaut);
    }

    @Override
    public boolean remove(Astronaut astronaut) {
        return astronauts.remove(astronaut);
    }

    @Override
    public Astronaut findByName(String name) {
        return astronauts.stream()
                .filter(astronaut -> astronaut.getName().equals(name)).findFirst().orElse(null);
    }
}

