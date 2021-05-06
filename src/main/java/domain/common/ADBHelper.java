package domain.common;

import data.repository.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ADBHelper {

    public int executeCommand(String command) throws InterruptedException, IOException {
        Process process = null;
        if (command.contains("\n")){
            String[] commands = command.split("\n");
            for(String nextCommand:commands){
                process = Runtime.getRuntime().exec(nextCommand);
            }
        }
        else {
            process = Runtime.getRuntime().exec(command);
        }
        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);
        Executors.newSingleThreadExecutor().submit(streamGobbler);
        return process.waitFor();
    }

    public String executeCommandForResult(String command) throws InterruptedException, IOException {
        StringBuilder result = new StringBuilder();
        Process process = Runtime.getRuntime().exec(command);
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9\\-]+)(\\s+)(device)");
        Matcher matcher;
        while ((line = in.readLine()) != null) {
            if (line.matches(pattern.pattern())) {
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    result.append(matcher.group(1));
                    result.append(" ");
                }
            }
        }
        return result.toString();
    }
}
