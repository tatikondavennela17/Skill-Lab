class GameState {
    private static GameState instance;

    private int level;
    private String difficulty;
    private GameState() {
        this.level = 1;
        this.difficulty = "Easy";
    }
    public static GameState getInstance() {
        if (instance == null) {
            synchronized (GameState.class) {
                if (instance == null) {
                    instance = new GameState();
                }
            }
        }
        return instance;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void displayGameState() {
        System.out.println("Level: " + level + ", Difficulty: " + difficulty);
    }
}
abstract class Enemy {
    public abstract void attack ();
}

class Zombie extends Enemy {
    public void attack () {
        System.out.println("Zombie attacks with a bite!");
    }
}

class Alien extends Enemy {
    public void attack () {
        System.out.println("Alien attacks with a laser!");
    }
}

abstract class EnemyFactory {
    public abstract Enemy createEnemy();
}

class ZombieFactory extends EnemyFactory {
    public Enemy createEnemy() {
        return new Zombie ();
    }
}

class AlienFactory extends EnemyFactory {
    public Enemy createEnemy() {
        return new Alien ();
    }
}
interface Weapon {
    void use ();
}

interface PowerUp {
    void activate ();
}

class LaserGun implements Weapon {
    public void use () {
        System.out.println("Using Laser Gun!");
    }
}

class PlasmaShield implements PowerUp {
    public void activate () {
        System.out.println("Activating Plasma Shield!");
    }
}

interface WeaponFactory {
    Weapon createWeapon();
    PowerUp createPowerUp();
}

class SciFiWeaponFactory implements WeaponFactory {
    public Weapon createWeapon() {
        return new LaserGun();
    }

    public PowerUp createPowerUp() {
        return new PlasmaShield();
    }
}
 class Main {
    public static void main(String[] args) {
        GameState gameState = GameState.getInstance();
        gameState.setLevel(2);
        gameState.setDifficulty("Hard");
        gameState.displayGameState();
        EnemyFactory zombieFactory = new ZombieFactory();
        Enemy zombie = zombieFactory.createEnemy();
        zombie.attack();

        EnemyFactory alienFactory = new AlienFactory();
        Enemy alien = alienFactory.createEnemy();
        alien.attack();
        WeaponFactory sciFiWeaponFactory = new SciFiWeaponFactory();
        Weapon laserGun = sciFiWeaponFactory.createWeapon();
        PowerUp plasmaShield = sciFiWeaponFactory.createPowerUp();

        laserGun.use();
        plasmaShield.activate();
    }
}