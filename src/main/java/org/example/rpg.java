package org.example;


class Character {
    private final String name;
    protected int hitPoints;

    public Character(String name, int hitPoints) {
        this.name = name;
        this.hitPoints = hitPoints;
    }

    public void attack(Character target) {
        System.out.println(name + " attacks " + target.getName() + "!");
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    public String getName() {
        return name;
    }
}

class Fighter extends Character {
    public Fighter(String name) {
        super(name, 100);
    }

    @Override
    public void attack(Character target) {
        int damage = 10;
        target.hitPoints -= damage;
        System.out.println(getName() + " strikes " + target.getName() + " dealing " + damage + " damage. " +
                target.getName() + " has " + target.hitPoints + " hit points remaining.");
    }
}

class Spell {
    private final String name;
    private final int damage;

    public Spell(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }
}

class Wizard extends Character {
    private final Spell[] spells;

    public Wizard(String name) {
        super(name, 80);
        spells = new Spell[]{new Spell("Pyro", 15)};
    }

    @Override
    public void attack(Character target) {
        System.out.println(getName() + " casts a spell!");
        castSpell(spells[0], target);
    }

    public void castSpell(Spell spell, Character target) {
        target.hitPoints -= spell.getDamage();
        System.out.println(getName() + " uses " + spell.getName() + " on " + target.getName() +
                " dealing " + spell.getDamage() + " damage. " + target.getName() +
                " has " + target.hitPoints + " hit points remaining.");
    }
}

class RPGGame {
    public static void main(String[] args) {
        Character fighter = new Fighter("Conan");
        Character wizard = new Wizard("Gandalf");

        fighter.attack(wizard);
        wizard.attack(fighter);
    }
}