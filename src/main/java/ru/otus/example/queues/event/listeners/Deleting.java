package ru.otus.example.queues.event.listeners;

import ru.otus.example.queues.model.Event;
import ru.otus.example.queues.Commons;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deleting implements Listener {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss.ssssss");

    private final String LOG_TEMPLATE = "%s - Event thread [%s] - Deleting: [%s] - List size: %d.\n";

    @Override
    public void onUpdate(Event data) {
        String log = String.format(
                LOG_TEMPLATE,
                simpleDateFormat.format(new Date()),
                Thread.currentThread().getName(),
                data.getItem().toString(),
                Commons.list.getSize()
        );
        System.out.printf("%s", log);

        try {
            Path logFile = Paths.get(Commons.DELETE_FILE_NAME);
            if (Files.exists(logFile)) {
                Files.write(
                        logFile,
                        log.getBytes(),
                        StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            System.out.println("Can't write file " + Commons.INSERT_FILE_NAME + ": " + e.getMessage());
        }
    }

}
