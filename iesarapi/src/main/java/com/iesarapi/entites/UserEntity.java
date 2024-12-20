package com.iesarapi.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_details")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String fullName;
    private String email;
    private String pazzword;
    private String phonNo;
    private char gender;
    private LocalDateTime dob;
    private String ssn;
    private String activeSw;
    private String activeStatus;
    private String roleName;
    @OneToMany(mappedBy = "user")
    private List<IesAppsEntity> iesAppsEntity;
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;
    @Column(insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedDate;
    private Integer createdBy;
    private Integer updatedBy;

}
