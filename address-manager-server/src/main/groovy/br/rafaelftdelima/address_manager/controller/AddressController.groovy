package br.rafaelftdelima.address_manager.controller

import br.rafaelftdelima.address_manager.entity.Address
import br.rafaelftdelima.address_manager.service.AddressService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/addresses")
class AddressController {

    private final AddressService addressService

    AddressController(AddressService addressService) {
        this.addressService = addressService
    }

    @GetMapping("/{id}")
    Address getAddressById(@PathVariable("id") Long id) {
        return addressService.getAddressById(id)
    }

    @GetMapping("/user/{userId}")
    List<Address> getAddressesByUserId(@PathVariable("userId") Long userId) {
        return addressService.getAddressesByUserId(userId)
    }

    @PostMapping("/user/{userId}")
    ResponseEntity<Address> createAddress(@PathVariable("userId") Long userId, @RequestBody Address address) {
        def created = addressService.createAddress(userId, address)
        return new ResponseEntity<>(created, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    Address updateAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        return ResponseEntity.ok(addressService.updateAddress(id, address))
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id)
        return ResponseEntity.noContent().build()
    }
}
