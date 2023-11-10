package com.petrinet.Entity;

import javax.persistence.*;
import java.util.List;

@Entity // This tells Hibernate to make a table out of this class
public class Transition {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private List<Location> inputLocations;

    @OneToMany
    private List<Location> outputLocations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Location> getInputLocations() {
        return inputLocations;
    }

    public void setInputLocations(List<Location> inputLocations) {
        this.inputLocations = inputLocations;
    }

    public List<Location> getOutputLocations() {
        return outputLocations;
    }

    public void setOutputLocations(List<Location> outputLocations) {
        this.outputLocations = outputLocations;
    }
}
