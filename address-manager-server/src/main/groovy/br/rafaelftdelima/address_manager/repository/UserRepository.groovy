package br.rafaelftdelima.address_manager.repository

import br.rafaelftdelima.address_manager.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByCpf(String cpf)
}
