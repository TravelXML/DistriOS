package com.districore.platform.sfa;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/sfa")
public class SfaController {
    private final SfaService service;

    public SfaController(SfaService service) {
        this.service = service;
    }

    @GetMapping("/my-beats")
    public ResponseEntity<ApiResponse<List<BeatPlan>>> myBeats() {
        return ResponseEntity.ok(ApiResponse.<List<BeatPlan>>builder()
                .success(true)
                .message("Beat plans retrieved")
                .data(service.getMyBeats())
                .build());
    }

    @GetMapping("/today-route")
    public ResponseEntity<ApiResponse<Object>> todayRoute() {
        return ResponseEntity.ok(ApiResponse.<Object>builder()
                .success(true)
                .message("Today's route retrieved")
                .data(service.getTodayRoute())
                .build());
    }

    @PostMapping("/check-in")
    public ResponseEntity<ApiResponse<String>> checkIn(@RequestParam String retailerId, @RequestParam double latitude, @RequestParam double longitude) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Checked in successfully")
                .data(service.checkIn(retailerId, latitude, longitude))
                .build());
    }

    @PostMapping("/check-out")
    public ResponseEntity<ApiResponse<String>> checkOut(@RequestParam String retailerId) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Checked out successfully")
                .data(service.checkOut(retailerId))
                .build());
    }

    @PostMapping("/retailer-visit")
    public ResponseEntity<ApiResponse<String>> retailerVisit(@RequestBody Object visit) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Retailer visit recorded")
                .data(service.recordRetailerVisit(visit))
                .build());
    }

    @PostMapping("/orders")
    public ResponseEntity<ApiResponse<String>> createOrder(@RequestBody Object order) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Order created")
                .data(service.createOrder(order))
                .build());
    }

    @PostMapping("/expenses")
    public ResponseEntity<ApiResponse<String>> submitExpense(@RequestBody Object expense) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Expense submitted")
                .data(service.submitExpense(expense))
                .build());
    }

    @GetMapping("/tasks")
    public ResponseEntity<ApiResponse<List<Object>>> tasks() {
        return ResponseEntity.ok(ApiResponse.<List<Object>>builder()
                .success(true)
                .message("Tasks retrieved")
                .data(service.getTasks())
                .build());
    }

    @PatchMapping("/tasks/{id}/complete")
    public ResponseEntity<ApiResponse<String>> completeTask(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<String>builder()
                .success(true)
                .message("Task completed")
                .data(service.completeTask(id))
                .build());
    }

    @GetMapping("/supervisor/dashboard")
    public ResponseEntity<ApiResponse<Object>> supervisorDashboard() {
        return ResponseEntity.ok(ApiResponse.<Object>builder()
                .success(true)
                .message("Supervisor dashboard retrieved")
                .data(service.getSupervisorDashboard())
                .build());
    }
}