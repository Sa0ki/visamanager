package com.visamanager.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.visamanager.models.Enums.StateOfApplication;
import com.visamanager.models.Enums.TypeOfApplication;

import java.time.LocalDate;

@Entity
@Table(name = "applications")
@Data @NoArgsConstructor @AllArgsConstructor
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dob;
    private TypeOfApplication type;
    private StateOfApplication state;
    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
