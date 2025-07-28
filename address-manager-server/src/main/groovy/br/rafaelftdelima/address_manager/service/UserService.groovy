package br.rafaelftdelima.address_manager.service

import br.rafaelftdelima.address_manager.entity.User
import br.rafaelftdelima.address_manager.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserService {

    private final UserRepository userRepository

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository
    }

    List<User> getAllUsers() {
        return userRepository.findAll()
    }

    User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow {
            new EntityNotFoundException("Usuário com id ${id} não encontrado.")
        }
    }

    @Transactional
    User createUser(User user) {
        def existing = userRepository.findByCpf(user.cpf)

        if (existing.isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado.")
        }

        return userRepository.save(user)
    }

    @Transactional
    User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow { new EntityNotFoundException("Usuário com ID $id não encontrado.") }

        if (user.cpf != userDetails.cpf) {
            def cpfCheck = userRepository.findByCpf(userDetails.cpf)

            if (cpfCheck.isPresent() && cpfCheck.get().id != id) {
                throw new IllegalArgumentException("CPF '${userDetails.cpf}' já está em uso.")
            }
        }

        user.name = userDetails.name
        user.cpf = userDetails.cpf

        return userRepository.save(user)
    }

    @Transactional
    void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário com ID $id não encontrado.")
        }

        userRepository.deleteById(id)
    }
}
