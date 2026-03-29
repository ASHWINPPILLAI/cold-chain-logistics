package com.logistics.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationApplication {

    private final List<String> alertLogs = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        return Map.of("status", "healthy", "service", "Notification Service");
    }

    @PostMapping("/alert")
    public Map<String, String> receiveAlert(@RequestBody Map<String, Object> payload) {
        String alertMessage = String.format("[%s] ALERT for Container %s: Temp reached %s", 
            LocalDateTime.now(), 
            payload.get("container_id"), 
            payload.get("temperature"));
        
        alertLogs.add(alertMessage);
        System.out.println(alertMessage); 
        
        return Map.of("message", "Alert received and processed successfully");
    }

    @GetMapping("/logs")
    public List<String> getAlertLogs() {
        return alertLogs;
    }
}