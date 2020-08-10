package spaceStation.core;

import spaceStation.common.ConstantMessages;
import spaceStation.common.ExceptionMessages;
import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;

import javax.sound.sampled.AudioSystem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    AstronautRepository astronautRepository = new AstronautRepository();
    PlanetRepository planetRepository = new PlanetRepository();
    private int exploredPlanetsCount = 0;


    public ControllerImpl() {
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
       if(type.equals(Biologist.class.getSimpleName())){
           astronaut = new Biologist(astronautName);
       }
       else if(type.equals(Geodesist.class.getSimpleName()))
           {
               astronaut = new Geodesist(astronautName);
            }
       else if(type.equals(Meteorologist.class.getSimpleName())){
           astronaut = new Meteorologist(astronautName);
        }
       else{
           throw new IllegalArgumentException("Astronaut type doesn't exists!");
       }
       astronautRepository.add(astronaut);
       return String.format("Successfully added %s: %s!", type,astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        planetRepository.add(planet);
        Collections.addAll(planet.getItems(), items);
        return String.format("Successfully added Planet: %s!", planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = astronautRepository.findByName(astronautName);
        if(astronaut == null){
            throw  new IllegalArgumentException(String.format
                    (ExceptionMessages.ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        else{
            astronautRepository.remove(astronaut);
            return String.format(String.format(ConstantMessages.ASTRONAUT_RETIRED, astronautName));
        }
    }

    @Override
    public String explorePlanet(String planetName) {
        Planet planet = planetRepository.findByName(planetName);
        List<Astronaut> astronautList = astronautRepository.getModels().stream().filter(astronaut -> astronaut.getOxygen() > 60)
                .collect(Collectors.toList());
        if(astronautList.isEmpty()){
            throw new IllegalArgumentException("You need at least one astronaut to explore the planet!");
        }
        exploredPlanetsCount++;
        int deadAstronauts = 0;
        Mission mission = new MissionImpl();
            mission.explore(planet, astronautList);
            deadAstronauts =
                    (int) astronautList.stream().filter(astronaut -> astronaut.getOxygen() <= 0).count();
        return String.format("Planet: %s was explored! " +
                "Exploration finished with %d dead astronauts!", planetName, deadAstronauts);


    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(exploredPlanetsCount).append(" planets were explored!").append(System.lineSeparator())
                .append("Astronauts info:").append(System.lineSeparator());
        String result = astronautRepository.getModels().stream().map(String::valueOf)
                .collect(Collectors.joining(System.lineSeparator()));
        sb.append(result);

        return sb.toString();
    }
}
