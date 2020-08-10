package spaceStation.repositories;

import spaceStation.models.planets.Planet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlanetRepository implements Repository<Planet> {
    private final List<Planet> planets = new ArrayList<>();


    @Override
    public Collection<Planet> getModels() {
        return Collections.unmodifiableCollection(planets);
    }

    @Override
    public void add(Planet planet) {
        planets.add(planet);
    }

    @Override
    public boolean remove(Planet planet) {
        return planets.remove(planet);
    }

    @Override
    public Planet findByName(String name) {
        return planets.stream()
                .filter(planet -> planet.getName().equals(name)).findFirst().orElse(null);
    }
}

