package com.legyver.fenxlib.api.config;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VersionAdapterTest {

    @Test
    public void adaptSemver2ToSemver1() {
        String version = "0.1.21-alpha.4+b0071";
        String result = new VersionAdapter(version)
                .adaptSemver1();
        assertThat(result).isEqualTo("0.1.21");
    }

    @Test
    public void adaptSemver1ToSemver1() {
        String version = "0.1.21";
        String result = new VersionAdapter(version)
                .adaptSemver1();
        assertThat(result).isEqualTo("0.1.21");
    }

    @Test
    public void handlesNullValues() {
        String result = new VersionAdapter(null)
                .adaptSemver1();
        assertThat(result).isNull();
    }
}
