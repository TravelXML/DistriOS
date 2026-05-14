package com.districore.platform.complaint;

import com.districore.platform.common.TenantAwareEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "complaints")
@Getter
@Setter
@NoArgsConstructor
public class Complaint extends TenantAwareEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String retailerId;
    private String category;
    private String description;
    private String assignedTo;
    private String resolutionNotes;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;
}
