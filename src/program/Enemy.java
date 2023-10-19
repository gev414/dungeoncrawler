package program;

import java.util.Random;

public class Enemy extends Entity implements Combat{
    private String difficulty;
    private int sdc;                 // smart decision chance

    private double dmg;

    String[] diff = {"easy","medium","hard"};
    Random rand = new Random();


    public Enemy() {
        super();
        this.difficulty = diff[rand.nextInt(diff.length)];
        doPower();
    }

    public String getDifficulty() {
        return difficulty;
    }



    private void doPower(){
        switch (this.difficulty){
            case "easy" -> {this.crit=6;this.sdc=25;}
            case "medium" -> {this.crit=10;this.sdc=50;}
            case "hard" -> {this.crit=15;this.sdc=75;}
            default -> {this.crit=6;this.sdc=25;}
        }
    }

    //                                     enemy logic
    public void decisionMake() {
        boolean decision = rand.nextInt(100) < sdc;

        if ((decision) && ((getHp() < 40) && (getAp() >= 1) && (lastHit > 22))) {
            block();
            setAp(getAp() - 1);
            return;
        }
        if ((decision) && ((getHp() < 70) && (getAp() >= 2) && (lastHit > 20))) {
            special();
            setAp(getAp() - 2);
            return;
        }
        if ((decision) && (getAp() > 1)) {
            heavyAttack();
            return;
        }

        normalAttack();
    }

    //                                       mechanics

    private boolean crit(){
            return rand.nextInt(100)<getCrit();
    }

    private void normalAttack(){
        double dmg = 7+rand.nextInt(10);
        if (crit()) dmg *= 1.5;
        this.dmg=dmg;
    }

    private void heavyAttack(){
        double dmg = 16+rand.nextInt(10);
        if (crit()) dmg *= 1.5;
        this.dmg=dmg;
    }

    @Override
    public void block() {
        if (getAp()>=1){
            isBlocking=true;
            setAp(getAp()-1);
        }
    }

    //special - recovers 50% of the health lost to the last hit taken
    @Override
    public void special() {
        if (getAp() >= 2) {
            setHp(getHp() + (lastHit * 0.5));
            setAp(getAp() - 2);
            System.out.println(getClass().getSimpleName() + " has used Recovery!");
        }
    }

    //                           functional

    @Override
    public double dmgDone() {
        return dmg;
    }

    public void setDmg(double dmg){
        this.dmg=dmg;
    }

    @Override
    public void dmgTaken(Combat player) {
        double temp = player.dmgDone();

        if (isBlocking) temp/=2;
        setHp(getHp()-temp);
        if (checkLife()){
            System.out.println(getClass().getSimpleName()+" ("+getDifficulty()+") took "+temp+" dmg");
            System.out.println("Enemy ("+getDifficulty()+") slain, proceeding to next round!");
            return;
        }
        lastHit=temp;
        System.out.println();
        System.out.println(getClass().getSimpleName()+" ("+getDifficulty()+") took "+temp+" dmg");
        System.out.println(getHp()+" hp: "+this.getAp()+" ap");
    }

    @Override
    public boolean checkLife() {
        return getHp() <= 0;
    }
}
