package com.seu_projeto.log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {
    private String message;
    private long timestamp;

    // Construtor que recebe uma mensagem e define o timestamp atual
    public LogEntry(String message) {
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    // Método que formata a entrada do log para uma representação legível
    public String formatLogEntry() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s", formatter.format(new Date(timestamp)), message);
    }
}
