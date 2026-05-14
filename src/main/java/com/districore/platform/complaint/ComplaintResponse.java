package com.districore.platform.complaint;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ComplaintResponse {
    private String id;
    private String retailerId;
    private String category;
    private String description;
    private String assignedTo;
    private String resolutionNotes;
    private ComplaintStatus status;
}
