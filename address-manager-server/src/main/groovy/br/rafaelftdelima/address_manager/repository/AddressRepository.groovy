package br.rafaelftdelima.address_manager.repository

import br.rafaelftdelima.address_manager.entity.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUserId(Long userId)
}
