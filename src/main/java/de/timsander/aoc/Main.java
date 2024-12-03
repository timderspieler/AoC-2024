package de.timsander.aoc;

import de.timsander.aoc.days.DayOne;
import de.timsander.aoc.days.DayThree;
import de.timsander.aoc.days.DayTwo;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Main {

    public static List<DayTemplate> days = new ArrayList<>();

    public static void main(String[] args) {
        new DayOne();
        new DayTwo();
        new DayThree();

        for (DayTemplate day : days) {
            day.run();
        }
    }
}