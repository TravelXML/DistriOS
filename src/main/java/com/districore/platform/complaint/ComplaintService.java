package com.districore.platform.complaint;

import com.districore.platform.common.ResourceNotFoundException;
import com.districore.platform.common.TenantContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ComplaintService {
    private final ComplaintRepository repository;

    public ComplaintService(ComplaintRepository repository) {
        this.repository = repository;
    }

    public ComplaintResponse createComplaint(ComplaintRequest request) {
        Complaint complaint = new Complaint();
        complaint.setRetailerId(request.getRetailerId());
        complaint.setCategory(request.getCategory());
        complaint.setDescription(request.getDescription());
        complaint.setStatus(ComplaintStatus.OPEN);
        complaint.setTenantId(TenantContext.getTenantId());
        repository.save(complaint);
        return toResponse(complaint);
    }

    public List<ComplaintResponse> listComplaints() {
        return repository.findByTenantId(TenantContext.getTenantId()).stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ComplaintResponse getComplaint(UUID id) {
        return toResponse(repository.findById(id).filter(c -> TenantContext.getTenantId().equals(c.getTenantId())).orElseThrow(() -> new ResourceNotFoundException("Complaint not found")));
    }

    public ComplaintResponse assignComplaint(UUID id, ComplaintStatusChangeRequest request) {
        Complaint complaint = find(id);
        complaint.setAssignedTo(request.getAssignedTo());
        complaint.setStatus(ComplaintStatus.ASSIGNED);
        return toResponse(complaint);
    }

    public ComplaintResponse resolveComplaint(UUID id, ComplaintStatusChangeRequest request) {
        Complaint complaint = find(id);
        complaint.setStatus(ComplaintStatus.RESOLVED);
        complaint.setResolutionNotes(request.getResolutionNotes());
        return toResponse(complaint);
    }

    public ComplaintResponse closeComplaint(UUID id) {
        Complaint complaint = find(id);
        complaint.setStatus(ComplaintStatus.CLOSED);
        return toResponse(complaint);
    }

    private Complaint find(UUID id) {
        return repository.findById(id).filter(c -> TenantContext.getTenantId().equals(c.getTenantId())).orElseThrow(() -> new ResourceNotFoundException("Complaint not found"));
    }

    private ComplaintResponse toResponse(Complaint complaint) {
        return new ComplaintResponse(complaint.getId().toString(), complaint.getRetailerId(), complaint.getCategory(), complaint.getDescription(), complaint.getAssignedTo(), complaint.getResolutionNotes(), complaint.getStatus());
    }
}
