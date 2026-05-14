package com.districore.platform.complaint;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ComplaintStatusChangeRequest {
    @NotBlank
    private String status;
    private String assignedTo;
    private String resolutionNotes;
}
