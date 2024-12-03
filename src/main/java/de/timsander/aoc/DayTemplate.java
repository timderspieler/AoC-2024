package de.timsander.aoc;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URI;
import java.util.Objects;

@Slf4j
@Getter
public abstract class DayTemplate {

    private int day;

    public DayTemplate(int day) {
        this.day = day;
        register();
    }

    public abstract void run();

    public void log(String msg) {
        log.info("[AoC Day {}] {}", day, msg);
    }

    private void register() {
        Main.days.add(this);
    }

    public File getInput(String name) {
        try {
            var resource = Objects.requireNonNull(getClass().getClassLoader().getResource("puzzleInput/%s.txt".formatted(name))).toURI();
            return new File(resource);
        } catch (Exception ignore) {
            return null;
        }
    }

}
