package br.rafaelftdelima.address_manager.controller

import br.rafaelftdelima.address_manager.entity.User
import br.rafaelftdelima.address_manager.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {

    private final UserService userService

    UserController(UserService userService) {
        this.userService = userService
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers()
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id))
    }

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user))
    }

    @PutMapping("/{id}")
    ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.updateUser(id, user))
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }
}
