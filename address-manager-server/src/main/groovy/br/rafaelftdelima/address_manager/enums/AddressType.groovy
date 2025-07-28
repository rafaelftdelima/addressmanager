package br.rafaelftdelima.address_manager.enums;

enum AddressType {
    HOME("Casa"),
    WORK("Trabalho"),
    SCHOOL("Escola/Universidade"),
    OTHER("Outro");

    final String description;

    AddressType(String description) {
        this.description = description;
    }
}
