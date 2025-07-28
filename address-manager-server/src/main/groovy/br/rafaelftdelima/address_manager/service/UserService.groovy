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
        def existing = userRepository.findByCpf(user.getCpf())

        if (existing.isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado.")
        }

        return userRepository.save(user)
    }

    @Transactional
    User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow { new EntityNotFoundException("Usuário com ID $id não encontrado.") }

        if (user.getCpf() != userDetails.getCpf()) {
            def cpfCheck = userRepository.findByCpf(userDetails.getCpf())

            if (cpfCheck.isPresent() && cpfCheck.get().getId() != id) {
                throw new IllegalArgumentException("CPF '${userDetails.getCpf()}' já está em uso.")
            }
        }

        user.setName(userDetails.getName())
        user.setCpf(userDetails.getCpf())

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
