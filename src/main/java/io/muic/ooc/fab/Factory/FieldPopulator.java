package io.muic.ooc.fab.Factory;

import io.muic.ooc.fab.Field;
import io.muic.ooc.fab.Location;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FieldPopulator {
    private static final Random RANDOM = new Random();

    private Map<Species,Double> probabilityMap = new HashMap<Species, Double>(){{
        Species[] animalTypes = Species.values();
        for (Species animalType : animalTypes) {
            put(animalType, animalType.getBreedingProbability());
        }
    }};
    public void populate(Field field, List<Methods> animals) {
        field.clear();
        for (int i = 0; i < field.getDepth(); i++) {
            for (int j = 0; j < field.getWidth(); j++) {
                for (Map.Entry<Species, Double> entry : probabilityMap.entrySet()){
                    if (RANDOM.nextDouble() <= entry.getValue()){
                        Location location = new Location(i, j);
                        Animal animal = AnimalFactory.createAnimal(entry.getKey(), field, location);
                        animals.add(animal);
                        break;
                    }
                }
            }
        }
    }
}
