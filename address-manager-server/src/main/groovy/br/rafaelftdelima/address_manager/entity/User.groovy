package br.rafaelftdelima.address_manager.entity

import jakarta.persistence.*

@Entity
@Table(name = "tb_user")
class User extends BaseEntity {

    @Column(nullable = false)
    String name

    @Column(nullable = false, unique = true)
    String cpf

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    List<Address> addressList = []

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getCpf() {
        return cpf
    }

    void setCpf(String cpf) {
        this.cpf = cpf
    }

    List<Address> getAddressList() {
        return addressList
    }

    void setAddressList(List<Address> addressList) {
        this.addressList = addressList
    }
}
