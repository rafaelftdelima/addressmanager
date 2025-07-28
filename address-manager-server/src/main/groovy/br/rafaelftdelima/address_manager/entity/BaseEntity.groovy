package br.rafaelftdelima.address_manager.entity

import jakarta.persistence.Column
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.PrePersist
import jakarta.persistence.PreUpdate

import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id

    @Column(nullable = false)
    private LocalDateTime creation

    @Column(name = "last_update", nullable = false)
    private LocalDateTime lastUpdate

    @PrePersist
    void onCreate() {
        def now = LocalDateTime.now()

        creation = now
        lastUpdate = now
    }

    @PreUpdate
    void onUpdate() {
        lastUpdate = LocalDateTime.now()
    }

    Long getId() {
        return id
    }

    void setId(Long id) {
        this.id = id
    }

    LocalDateTime getCreation() {
        return creation
    }

    void setCreation(LocalDateTime creation) {
        this.creation = creation
    }

    LocalDateTime getLastUpdate() {
        return lastUpdate
    }

    void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate
    }
}
