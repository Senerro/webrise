package com.webrise.assignment.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    public UUID id;

    @Column(name = "EMAIL", unique = true)
    @Email(message = "Неверный электронный адрес")
    @Size(max = 100)
    private String email;

    @Column(name = "ENCRYPTED_PASSWORD", length = 60, nullable = false)
    @Size(max = 60, min = 60)
    private String encryptedPassword;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_subscription",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SUBSCRIPTION_ID")
    )
    private List<Subscription> subscriptions;
}
