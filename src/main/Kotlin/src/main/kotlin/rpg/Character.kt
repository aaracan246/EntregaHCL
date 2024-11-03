package org.example.rpg

open class Character(val name: String, var hitPoints: Int) {
    open fun attack(target: Character) {
        println("$name attacks ${target.name}!")
    }

    fun isAlive(): Boolean {
        return hitPoints > 0
    }
}