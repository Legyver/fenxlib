package com.legyver.fenxlib.widgets.filetree.icons;

import com.legyver.fenxlib.api.icons.application.NamedPredicate;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Construct a named predicate to check if a filename matches a pattern
 * All checks are performed on the uppercase version, so are not case-sensitive.
 */
public class FileExtensionPredicate implements NamedPredicate<String> {
    private final String name;
    private final String suffixUC;
    private final Pattern suffixPattern;

    /**
     * Construct a named predicate to check if a filename matches a pattern
     * Example patterns:
     *  Match all files ending in .pdf: ".pdf"
     *  Match all files ending in either .xls or .xlsx, ".xls|.xlsx"

     * @param name the name of the predicate.  Named predicates can be overridden by name
     * @param suffixes the suffixes to check
     */
    public FileExtensionPredicate(String name, String suffixes) {
        this.name = name;
        this.suffixUC = suffixes.toUpperCase(Locale.ROOT).replaceAll("\\.", "\\\\.");
        this.suffixPattern = Pattern.compile("(" + suffixUC + ")$");
    }

    @Override
    public boolean test(String filename) {
        Matcher matcher = suffixPattern.matcher(filename.toUpperCase(Locale.ROOT));
        if (matcher.find()) {
           return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }
}
