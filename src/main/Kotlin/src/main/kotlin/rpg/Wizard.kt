package org.example.rpg

class Wizard(name: String) : Character(name, 80) {
    private val spells = mutableListOf<Spell>()

    init {

        spells.add(Spell("Pyro", 15))
    }

    override fun attack(target: Character) {
        println("$name casts a spell!")
        castSpell(spells[0], target)
    }

    fun castSpell(spell: Spell, target: Character) {
        target.hitPoints -= spell.damage
        println("$name uses ${spell.name} on ${target.name} dealing ${spell.damage} damage. " +
                "${target.name} has ${target.hitPoints} hit points remaining.")
    }
}