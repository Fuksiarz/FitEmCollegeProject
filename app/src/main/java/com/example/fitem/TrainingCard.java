package com.example.fitem;

import java.util.ArrayList;

public class TrainingCard {
    private String cardName;
    private ArrayList<String> workouts;

    public TrainingCard(String cardName) {
        this.cardName = cardName;
        this.workouts = new ArrayList<>();
    }

    public String getCardName() {
        return cardName;
    }

    public ArrayList<String> getWorkouts() {
        return workouts;
    }

    public void addWorkout(String workout) {
        workouts.add(workout);
    }

    public void removeWorkout(String workout) {
        workouts.remove(workout);
    }
}