package tn.iit.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "t_compte")
public class Compte implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id

    @EqualsAndHashCode.Include
    private Integer rib;

    @Column(name = "client")
    private String nomClient;

    private float solde;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

   
}
