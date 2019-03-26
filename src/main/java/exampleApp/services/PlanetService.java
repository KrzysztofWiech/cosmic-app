package exampleApp.services;

import exampleApp.mappers.PlanetMapper;
import exampleApp.models.Planet;
import exampleApp.models.dtos.PlanetDto;
import exampleApp.repositories.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanetService {

    private PlanetRepository planetRepository;
    private PlanetMapper planetMapper;

    public PlanetService(PlanetRepository planetRepository, PlanetMapper planetMapper) {
        this.planetRepository = planetRepository;
        this.planetMapper = planetMapper;
    }

    /*
     *
     *
     *DAO
     *
     *
     */

    public List<Planet> getPlanets() {
        return planetRepository.findAll();
    }

    /*
     *
     *
     *DTO
     *
     *
     */

    public List<PlanetDto> getPlanetsDto() {

        return planetRepository
                .findAll()
                .stream()
                .map(planetMapper::map)
                .collect(Collectors.toList());

    }

    public Planet addPlanet(PlanetDto planetDto) {
        return planetRepository.save(planetMapper.revers(planetDto));
    }

    public void deletePlanet(String planetName) {
        planetRepository.deleteByPlanetName(planetName);
    }

    public void updatePlanet(PlanetDto planetDto) {
        planetRepository
                .findByPlanetName(planetDto.getPlanetName())
                .ifPresent(p -> {
                    p.setDistanceFromSun(planetDto.getDistanceFromSun());
                    p.setOneWayLightTimeToTheSun(planetDto.getOneWayLightTimeToTheSun());
                    p.setLengthOfYear(planetDto.getLengthOfYear());
                    p.setPlanetType(planetDto.getPlanetType());
                    p.setPlanetInfo(planetDto.getPlanetInfo());
                    p.setPlanetImage(planetDto.getPlanetImage());

                    planetRepository.save(p);
                });
    }

    public List<PlanetDto> getPlanetsByDistanceFromSun(Long distance) {
        return planetRepository
                .findPlanetsByDistanceFromSun(distance)
                .stream()
                .map(planetMapper::map)
                .collect(Collectors.toList());
    }

}
