package com.example.padaria;

import com.vistajet.airportcentre.core.util.ObjectBuilder;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestUtils {
    private TestUtils() {
        throw new AssertionError();
    }

    public static void assertNotEqualObjects(ObjectBuilder builder1, ObjectBuilder builder2) {
        final Object object1 = builder1.build();
        final Object object2 = builder2.build();

        assertNotEquals(object1, object2);
        assertNotEquals(object2, object1);
    }

    public static void assertEqualObjects(ObjectBuilder builder1, ObjectBuilder builder2) {
        final Object object1 = builder1.build();
        final Object object2 = builder2.build();

        assertEquals(object1, object2);
        assertEquals(object2, object1);
        assertEquals(object1.hashCode(), object2.hashCode());
    }

    public static String readFile(String pathRelativeToResource) throws IOException {
        final File file = ResourceUtils.getFile("classpath:" + pathRelativeToResource);
        return new String(Files.readAllBytes(file.toPath()));
    }
}
