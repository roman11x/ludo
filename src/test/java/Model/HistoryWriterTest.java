package Model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HistoryWriterTest {

    @Test
    void createsFileIfNotExists(@TempDir Path tempDir) throws IOException {
        String expectedHeader = "gameID,difficulty,"
                + "timestamp(DD/MM/YYYY; HH:MM:SS),"
                + "1st_place_name,2nd_place_name,3rd_place_name,4rd_place_name,"
                + "game_duration(HH:MM:SS)";

        // resolve(...) = "an address inside this folder"; the file isn't created yet
        Path file = tempDir.resolve("history.csv");


        var writer = new HistoryWriter(file);
        writer.createFileIfNotExists();

        List<String> lines = Files.readAllLines(file);

        assertTrue(Files.exists(file));
        assertEquals(expectedHeader, lines.getFirst());
        assertEquals(1, lines.size()); // make sure the file only has the header
    }

    @Test
    void createsDirectoriesIfNotExists(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("folder1")
                .resolve("folder2")
                .resolve("history.csv");

        var writer = new HistoryWriter(file);
        writer.createFileIfNotExists();

        assertTrue(Files.exists(file));
    }

    @Test
    void ifFileExistsDoesNothing(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("history.csv");

        Files.writeString(file, "some content");

        var writer = new HistoryWriter(file);
        writer.createFileIfNotExists();

        List<String> lines = Files.readAllLines(file);

        assertEquals("some content", lines.getFirst());
        assertEquals(1, lines.size()); // make sure the file only has the sample
    }
}
