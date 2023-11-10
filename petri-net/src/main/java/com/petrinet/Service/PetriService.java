package com.petrinet.Service;

import com.petrinet.Entity.Location;
import com.petrinet.Entity.Transition;

import java.util.List;

public interface PetriService {
    public abstract String addNewLocation(Integer tokens);
    public abstract String editLocation (Location location, Integer tokens);
    public abstract Iterable<Location> getAllLocations();
    public abstract String addNewTransition(List<Location> inputLocations, List<Location> outputLocations);
    public abstract String editTransition(Transition transition, List<Location> inputLocations, List<Location> outputLocations);
    public abstract Iterable<Transition> getAllTransitions();
    public abstract List<Integer> getCurrentMarking();
    public abstract String executeTransition(Transition transition);
    public abstract String executePetriNet();
}