package com.groupsystem.portal.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "programs")
public class ProgramEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subsystem_id", nullable = false)
    private Subsystem subsystem;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Subsystem getSubsystem() { return subsystem; }
    public void setSubsystem(Subsystem subsystem) { this.subsystem = subsystem; }
}
