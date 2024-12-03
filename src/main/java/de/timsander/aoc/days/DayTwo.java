package de.timsander.aoc.days;

import de.timsander.aoc.DayTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DayTwo extends DayTemplate {

    private List<Integer[]> levels = new ArrayList<>();
    private int safeReports = 0;

    public DayTwo() {
        super(2);
    }

    @Override
    public void run() {

        loadLevels();

        levels.forEach(level -> {
            if (isSafeLevel(level)) {
                safeReports++;
            }
        });

        log("Part One: There are %d safe reports".formatted(safeReports));
    }

    private void loadLevels() {
        try (var sc = new Scanner(getInput("dayTwo"))) {
            while (sc.hasNextLine()) {
                var line = sc.nextLine().split(" ");
                var levelLine = Arrays.stream(line)
                        .map(Integer::parseInt)
                        .toArray(Integer[]::new);
                levels.add(levelLine);
            }
        } catch (Exception e) {
            log(e.getMessage());
        }

    }

    private boolean isSafeLevel(Integer[] level) {
        var foundUnsafeAttributes = 0;
        var lastInt = -1;
        var mode = "";
        for (int i : level) {

            // No previous level to compare to
            if (lastInt == -1) {
                lastInt = i;
                continue;
            }

            // Check if difference between current and last level is greater than 3 or 0
            if (Math.abs(lastInt - i) > 3 || Math.abs(lastInt - i) == 0) {
                foundUnsafeAttributes++;
            }

            // Check if the levels are increasing or decreasing
            if (lastInt < i) {
                if (mode.equals("decreasing")) {
                    foundUnsafeAttributes++;
                } else if (mode.isBlank()) {
                    mode = "increasing";
                }
            } else if (lastInt > i) {
                if (mode.equals("increasing")) {
                    foundUnsafeAttributes++;
                } else if (mode.isBlank()) {
                    mode = "decreasing";
                }
            }

            lastInt = i;
        }

        return foundUnsafeAttributes == 0;
    }

}
