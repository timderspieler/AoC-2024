package de.timsander.aoc.days;

import de.timsander.aoc.DayTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class DayThree extends DayTemplate {

    private static final String REGEX = "mul\\(\\d{1,3},\\d{1,3}\\)";
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

//            var pat = Pattern.compile("(mul\\((\\d+),(\\d+)\\))|(do\\(\\))|(don't\\(\\))");
//            var matcher = pat.matcher(input);
//
//            var enabled = true;
//
//            while (matcher.find()) {
//                if (matcher.group().equals("don't()")) {
//                    log("Dont activated!");
//                    enabled = false;
//                } else if (matcher.group().equals("do()")) {
//                    log("Do activated!");
//                    enabled = true;
//                } else if (enabled) {
//                    log("Enabled, executing " + matcher.group());
//                    result += mul(matcher.group());
//                } else {
//                    log("Skipping " + matcher.group());
//                }
//            }

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

    private int executeMul(String instruction) {
        var result = 0;
        var pattern = Pattern.compile(REGEX);
        var matcher = pattern.matcher(instruction);
        while (matcher.find()) {
            result += mul(matcher.group());
        }
        return result;
    }

    private int mul(String mulInstruction) {
        var numString = mulInstruction.replaceAll("mul\\(", "").replaceAll("\\)", "");
        var nums = numString.split(",");
        return parseInt(nums[0]) * parseInt(nums[1]);
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
