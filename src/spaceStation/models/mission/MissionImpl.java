package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.Repository;

import java.util.Collection;

public class MissionImpl implements Mission{


    @Override
    public void explore(spaceStation.models.planets.Planet planet, Collection<Astronaut> astronauts) {
        for (Astronaut astronaut : astronauts) {
            if(astronaut.canBreath()) {
                if (planet.getItems().size() > 0) {
                    collectItem(astronaut, planet);
                }
            }
        }

    }

    private void collectItem(Astronaut astronaut, Planet planet) {
        while(astronaut.canBreath() && !planet.getItems().isEmpty()) {
            String item = planet.getItems().stream().findFirst().orElse(null);
            if (astronaut.canBreath()) {
                astronaut.breath();
                planet.getItems().remove(item);
                astronaut.getBag().getItems().add(item);
            }
        }
    }
}
