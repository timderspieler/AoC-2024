package de.timsander.aoc.days;

import de.timsander.aoc.DayTemplate;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class DayOne extends DayTemplate {

    private ArrayList<Integer> listOne = new ArrayList<>();
    private ArrayList<Integer> listTwo = new ArrayList<>();

    public DayOne() {
        super(1);
    }

    @Override
    public void run() {

        loadLists();

        // Sort the lists by the natural order
        listOne.sort(Comparator.naturalOrder());
        listTwo.sort(Comparator.naturalOrder());

        calculateDistance();
        calculateSimilarity();
    }

    private void loadLists() {
        try (var sc = new Scanner(getInput("dayOne"))) {
            while (sc.hasNextLine()) {
                var line = sc.nextLine().split(" {3}");
                listOne.add(Integer.parseInt(line[0]));
                listTwo.add(Integer.parseInt(line[1]));
            }
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    private void calculateDistance() {
        int distance = 0;
        for (int i = 0; i < listOne.size(); i++) {
            distance += Math.abs(listOne.get(i) - listTwo.get(i));
        }
        log("Part One: The distance between the two lists is " + distance);
    }

    private void calculateSimilarity() {
        var similarityScore = 0;

        for (int number : listOne) {
            int count = 0;
            for (int number2 : listTwo) {
                if (number2 == number) {
                    count++;
                }
            }
            similarityScore += count * number;
        }

        log("Part Two: The similarity score is " + similarityScore);
    }

}
