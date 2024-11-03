package org.example.rpg

class Fighter(name: String): Character(name, 100) {
    override fun attack(target: Character) {
        val damage = 10
        target.hitPoints -= damage
        println("$name strikes ${target.name} dealing $damage damage. " +
                "${target.name} has ${target.hitPoints} hit points remaining.")
    }
}