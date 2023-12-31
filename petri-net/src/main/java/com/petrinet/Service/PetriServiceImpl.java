package com.petrinet.Service;

import com.petrinet.Entity.Location;
import com.petrinet.Entity.Transition;
import com.petrinet.Repository.LocationRepository;
import com.petrinet.Repository.TransitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetriServiceImpl implements PetriService {
    @Autowired // This means to get the bean called locationRepository which is auto-generated by Spring, we will use it to handle the data
    private LocationRepository locationRepository;

    @Autowired
    private TransitionRepository transitionRepository;

    @Override
    public String addNewLocation(Integer tokens) {
        Location l = new Location();
        l.setTokens(tokens);
        locationRepository.save(l);
        return "Saved new location";
    }

    @Override
    public String editLocation(Location location, Integer tokens) {
        location.setTokens(tokens);
        locationRepository.save(location);
        return "Location edited";
    }

    @Override
    public Iterable<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public String addNewTransition(List<Location> inputLocations, List<Location> outputLocations) {
        Transition t = new Transition();
        t.setInputLocations(inputLocations);
        t.setOutputLocations(outputLocations);
        transitionRepository.save(t);
        return "Saved new transition";
    }

    @Override
    public String editTransition(Transition transition, List<Location> inputLocations, List<Location> outputLocations) {
        transition.setInputLocations(inputLocations);
        transition.setOutputLocations(outputLocations);
        transitionRepository.save(transition);
        return "Transition edited";
    }

    @Override
    public Iterable<Transition> getAllTransitions() {
        return transitionRepository.findAll();
    }

    @Override
    public List<Integer> getCurrentMarking() {
        List<Integer> currentMarking = new ArrayList<>();
        Iterable<Location> locations = locationRepository.findAll();
        for (Location l: locations) {
            currentMarking.add(l.getTokens());
        }
        return currentMarking;
    }

    @Override
    public String executeTransition(Transition transition) {
        boolean executable = true;
        Iterable<Location> inputLocations = transition.getInputLocations();
        Iterable<Location> outputLocations = transition.getOutputLocations();
        for (Location l: inputLocations) {
            if (l.getTokens() == 0) {
                executable = false;
            }
        }
        if (executable == true) {
            for (Location l: inputLocations) {
                l.setTokens(l.getTokens() - 1);
                locationRepository.save(l);
            }
            for (Location l: outputLocations) {
                l.setTokens(l.getTokens() + 1);
                locationRepository.save(l);
            }
            return "Transition executed";
        }
        else {
            return "Transition not executable";
        }
    }

    @Override
    public String executePetriNet() {
        Iterable<Transition> transitions = transitionRepository.findAll();
        for (Transition t: transitions) {
            executeTransition(t);
        }
        return "Petri Net executed";
    }
}
