package com.seu_projeto.log;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class LogRepositoryTest {

    @Autowired
    private LogRepository logRepository;

    @Test
    public void testSalvarLog() {
        LogEntry log = new LogEntry();
        log.setMessage("Teste de Log");
        log.setDetails("Detalhes do log de teste");
        log.setTimestamp(LocalDateTime.now());

        log = logRepository.save(log);

        assertNotNull(log.getId(), "O ID do log deveria ter sido gerado.");
        assertEquals("Teste de Log", log.getMessage(), "A mensagem do log não está correta.");
    }
}
