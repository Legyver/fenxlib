package com.legyver.fenxlib.copybuildversion;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModuleInfoProcessor {
    private static final Pattern pattern = Pattern.compile("^module(\\s)+(([a-zA-Z\\.0-9])*)");
    private final Scanner scanner;

    public ModuleInfoProcessor(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getModuleName() {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            line = line.trim();
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                return matcher.group(2).trim();
            }
        }
        return null;
    }
}
