package com.seu_projeto.log;

import java.util.ArrayList;
import java.util.List;

public class LogRepository {
    private List<LogEntry> logs = new ArrayList<>();

    public void addLog(LogEntry entry) {
        logs.add(entry);
    }

    public List<LogEntry> getLogs() {
        return logs;
    }
}
