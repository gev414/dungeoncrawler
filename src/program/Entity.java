package program;

public abstract class Entity {
    protected String name;
    protected double hp;   //health points
    protected int ap;   //action points

    protected double lastHit;
    protected boolean isBlocking;

    protected int crit;

//    protected double damage;

    //             constructor for the player
    public Entity(String name) {
        this.hp = 100;
        this.ap = 3;
        this.crit = 10;
        this.name = name;
        this.lastHit = 0;
        this.isBlocking = false;

    }

    //                constructor for the enemy
    public Entity() {
        this.hp = 100;
        this.ap = 0;
        this.lastHit = 0;
        this.isBlocking = false;
    }

//    public double getDamage() {
//        return damage;
//    }

  //  public void setDamage(double damage) {
  //      this.damage = damage;
  //  }

    public double getHp() {
        return hp;
    }

    public int getAp() {
        return ap;
    }

    public int getCrit() {
        return crit;
    }

    public String getName() {
        return name;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public boolean checkLife(){
        return false;
    }



}
