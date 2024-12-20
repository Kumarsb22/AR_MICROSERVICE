package com.iesarapi.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.awt.image.ImageProducer;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "IES_APP_DETAIlS")
public class IesAppsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long caseNo;
    private String fullName;
    private String phoneNO;
    private LocalDateTime dob;
    private String gender;
    private String email;
    private String ssn;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private Integer createdBy;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private Integer updatedBy;

    
}
