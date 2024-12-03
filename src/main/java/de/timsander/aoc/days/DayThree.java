package de.timsander.aoc.days;

import de.timsander.aoc.DayTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class DayThree extends DayTemplate {
    private final List<String> instructions = new ArrayList<>();

    public DayThree() {
        super(3);
    }

    @Override
    public void run() {
        loadInput();

        var withDo = withDo();
        var without = withoutDo();

        log("Part One: The result is %d".formatted(without));
        log("Part Two: The result is %d".formatted(withDo));

    }

    private int withoutDo() {
        var result = 0;
        for (String instruction : instructions) {
            result += solve(instruction, true);
        }
        return result;
    }

    private int withDo() {
        var result = 0;

        for (var input : instructions) {
            result += solve(input, false);
        }

        return result;
    }

    private void loadInput() {
        try (var sc = new Scanner(getInput("dayThree"))) {
            while (sc.hasNextLine()) {
                instructions.add(sc.nextLine());
            }
        } catch (Exception e) {
            log(e.getMessage());
        }
    }

    private int solve(String input, boolean ignoreConditions) {
        Pattern pattern = Pattern.compile("(mul\\((\\d+),(\\d+)\\))|(do\\(\\))|(don't\\(\\))");
        Matcher matcher = pattern.matcher(input);
        AtomicBoolean isEnabled = new AtomicBoolean(true);

        return matcher.results()
                .mapToInt(result -> {
                    if (result.group(1) != null && (ignoreConditions || isEnabled.get())) {
                        return parseInt(result.group(2)) * parseInt(result.group(3));
                    } else if (!ignoreConditions) {
                        if (result.group(4) != null) {
                            isEnabled.set(true);
                        } else if (result.group(5) != null) {
                            isEnabled.set(false);
                        }
                    }
                    return 0;
                })
                .sum();
    }

}
