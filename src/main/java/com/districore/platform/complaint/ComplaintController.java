package com.districore.platform.complaint;

import com.districore.platform.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/complaints")
public class ComplaintController {
    private final ComplaintService service;

    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ComplaintResponse>> create(@Valid @RequestBody ComplaintRequest request) {
        return ResponseEntity.ok(ApiResponse.<ComplaintResponse>builder()
                .success(true)
                .message("Complaint created")
                .data(service.createComplaint(request))
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ComplaintResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.<List<ComplaintResponse>>builder()
                .success(true)
                .message("Complaints retrieved")
                .data(service.listComplaints())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ComplaintResponse>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<ComplaintResponse>builder()
                .success(true)
                .message("Complaint retrieved")
                .data(service.getComplaint(id))
                .build());
    }

    @PatchMapping("/{id}/assign")
    public ResponseEntity<ApiResponse<ComplaintResponse>> assign(@PathVariable UUID id, @Valid @RequestBody ComplaintStatusChangeRequest request) {
        return ResponseEntity.ok(ApiResponse.<ComplaintResponse>builder()
                .success(true)
                .message("Complaint assigned")
                .data(service.assignComplaint(id, request))
                .build());
    }

    @PatchMapping("/{id}/resolve")
    public ResponseEntity<ApiResponse<ComplaintResponse>> resolve(@PathVariable UUID id, @Valid @RequestBody ComplaintStatusChangeRequest request) {
        return ResponseEntity.ok(ApiResponse.<ComplaintResponse>builder()
                .success(true)
                .message("Complaint resolved")
                .data(service.resolveComplaint(id, request))
                .build());
    }

    @PatchMapping("/{id}/close")
    public ResponseEntity<ApiResponse<ComplaintResponse>> close(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.<ComplaintResponse>builder()
                .success(true)
                .message("Complaint closed")
                .data(service.closeComplaint(id))
                .build());
    }
}
