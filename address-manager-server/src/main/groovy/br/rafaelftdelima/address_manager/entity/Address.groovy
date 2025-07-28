package br.rafaelftdelima.address_manager.entity

import br.rafaelftdelima.address_manager.enums.AddressType
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "tb_address")
class Address extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user

    @Column(nullable = false)
    private String address

    @Column(nullable = false)
    private String complement

    @Column(nullable = false)
    private String neighborhood

    @Column(nullable = false)
    private String cep

    @Column(nullable = false)
    private String city

    @Column(nullable = false)
    private String state

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AddressType type

    User getUser() {
        return user
    }

    void setUser(User user) {
        this.user = user
    }

    String getAddress() {
        return address
    }

    void setAddress(String address) {
        this.address = address
    }

    String getComplement() {
        return complement
    }

    void setComplement(String complement) {
        this.complement = complement
    }

    String getNeighborhood() {
        return neighborhood
    }

    void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood
    }

    String getCep() {
        return cep
    }

    void setCep(String cep) {
        this.cep = cep
    }

    String getCity() {
        return city
    }

    void setCity(String city) {
        this.city = city
    }

    String getState() {
        return state
    }

    void setState(String state) {
        this.state = state
    }

    AddressType getType() {
        return type
    }

    void setType(AddressType type) {
        this.type = type
    }
}
