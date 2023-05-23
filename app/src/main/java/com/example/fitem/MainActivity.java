package com.example.fitem;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private EditText editTextWorkoutName;
    private Button buttonAddWorkout;
    private ListView listViewWorkouts;
    private ArrayList<String> workoutsList;
    private ArrayAdapter<String> workoutsAdapter;

    private static final String FILE_NAME = "workouts.txt";

    private void saveWorkoutsToFile() {
        File file = new File(getExternalFilesDir(null), FILE_NAME);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (String workout : workoutsList) {
                writer.write(workout);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadWorkoutsFromFile() {
        File file = new File(getExternalFilesDir(null), FILE_NAME);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                workoutsList.add(line);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicjalizacja elementów interfejsu użytkownika
        editTextWorkoutName = findViewById(R.id.editTextWorkoutName);
        buttonAddWorkout = findViewById(R.id.buttonAddWorkout);
        listViewWorkouts = findViewById(R.id.listViewWorkouts);

        // Inicjalizacja listy treningów
        workoutsList = new ArrayList<>();
        workoutsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workoutsList);
        listViewWorkouts.setAdapter(workoutsAdapter);
        loadWorkoutsFromFile();
        workoutsAdapter.notifyDataSetChanged();

        // Obsługa przycisku "Dodaj trening"
        buttonAddWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workoutName = editTextWorkoutName.getText().toString();

                if (!workoutName.isEmpty()) {
                    workoutsList.add(workoutName);
                    workoutsAdapter.notifyDataSetChanged();
                    editTextWorkoutName.setText("");

                    saveWorkoutsToFile();
                }
            }

        });
    }
}