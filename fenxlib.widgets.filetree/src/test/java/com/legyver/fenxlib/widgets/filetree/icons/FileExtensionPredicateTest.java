package com.legyver.fenxlib.widgets.filetree.icons;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class FileExtensionPredicateTest {

    @Test
    public void singleExtension() {
        Predicate<String> predicate = new FileExtensionPredicate("gunzip", ".gz");
        assertThat(predicate.test("name.gz")).isTrue();
        assertThat(predicate.test("nam.egz")).isFalse();
    }

    @Test
    public void orFileExtensions() {
        Predicate<String> predicate = new FileExtensionPredicate("zip", ".gz|.7z|.zip");
        assertThat(predicate.test("name.gz")).isTrue();
        assertThat(predicate.test("nam.egz")).isFalse();
        assertThat(predicate.test("name.7z")).isTrue();
        assertThat(predicate.test("name.7gz")).isFalse();
        assertThat(predicate.test("name7.z")).isFalse();
        assertThat(predicate.test("name7.zip")).isTrue();
        assertThat(predicate.test("name7.7zip")).isFalse();
        assertThat(predicate.test("name.tar.gz")).isTrue();
    }

    @Test
    public void tarBalls() {
        Predicate<String> predicate = new FileExtensionPredicate("tarball", ".tar.gz");
        assertThat(predicate.test("name.tar.gz")).isTrue();
        assertThat(predicate.test("name.tar")).isFalse();
        assertThat(predicate.test("name.gz")).isFalse();
    }
}
