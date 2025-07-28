package br.rafaelftdelima.address_manager.service

import br.rafaelftdelima.address_manager.entity.Address
import br.rafaelftdelima.address_manager.repository.AddressRepository
import br.rafaelftdelima.address_manager.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AddressService {

    private final AddressRepository addressRepository
    private final UserRepository userRepository

    AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository
        this.userRepository = userRepository
    }

    Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow { new EntityNotFoundException("Endereço com ID $id não encontrado.") }
    }

    List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.findAllByUserId(userId)
    }

    @Transactional
    Address createAddress(Long userId, Address address) {
        def user = userRepository.findById(userId)
                .orElseThrow { new EntityNotFoundException("Usuário com ID $userId não encontrado.") }

        address.user = user
        return addressRepository.save(address)
    }

    @Transactional
    Address updateAddress(Long id, Address updatedAddress) {
        Address address = getAddressById(id)

        address.address = updatedAddress.address
        address.complement = updatedAddress.complement
        address.neighborhood = updatedAddress.neighborhood
        address.city = updatedAddress.city
        address.cep = updatedAddress.cep
        address.state = updatedAddress.state
        address.type = updatedAddress.type

        return addressRepository.save(address)
    }

    @Transactional
    void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new EntityNotFoundException("Endereço com ID $id não encontrado.")
        }
        addressRepository.deleteById(id)
    }
}
