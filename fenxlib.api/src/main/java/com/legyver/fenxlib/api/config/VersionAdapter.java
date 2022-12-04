package com.legyver.fenxlib.api.config;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Adapter a version from SemVer 2.0 to SemVer 1.0
 */
public class VersionAdapter {
    private final String version;
    private static final Pattern semver1 = Pattern.compile("^(\\d{1,5})\\.(\\d{1,5})\\.(\\d{1,5})$");
    private static final Pattern semver2 = Pattern.compile("^(\\d{1,5})\\.(\\d{1,5})\\.(\\d{1,5})(-|\\+)");


    /**
     * Construct a version adapter for a version
     * @param version the version to adapt
     */
    public VersionAdapter(String version) {
        this.version = version;
    }

    /**
     * @return the adapted version
     */
    public String adaptSemver1() {
        if (version == null) {
            return null;
        }
        Matcher semver1Matcher = semver1.matcher(version);
        if (semver1Matcher.find()) {
            return version;
        }
        Matcher semver2Matcher = semver2.matcher(version);
        if (semver2Matcher.find()) {
            return new StringJoiner(".")
                    .add(semver2Matcher.group(1))
                    .add(semver2Matcher.group(2))
                    .add(semver2Matcher.group(3))
                    .toString();
        }
        return null;
    }
}
