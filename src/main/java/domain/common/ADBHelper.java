package domain.common;

import data.repository.StreamGobbler;

import java.io.IOException;
import java.util.concurrent.Executors;

public class ADBHelper {

    public int executeCommand(String command) throws InterruptedException, IOException {
        Process process = Runtime.getRuntime().exec(command);
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        return process.waitFor();
    }
}
