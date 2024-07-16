package program;

import java.util.ArrayList;
import java.util.Random;

public class Enemy extends Entity implements Combat{
    private String difficulty;
    private int sdc;                 // smart decision chance

    private double dmg;

    String[] names = {"mBob","Steve","Howard","Marduk","Memchok","Frank"};
    String[] diff = {"easy","medium","hard"};
    Random rand = new Random();


    public Enemy() {
        super();
        this.name= names[rand.nextInt(5)];
        this.difficulty = diff[rand.nextInt(diff.length)];
        doPower();
    }

    public String getDifficulty() {
        return difficulty;
    }



    private void doPower(){
        switch (this.difficulty){
            case "easy" -> {this.crit=6;this.sdc=25;this.ap=1;}
            case "medium" -> {this.crit=10;this.sdc=50;this.ap=2;}
            case "hard" -> {this.crit=15;this.sdc=75;this.ap=4;}
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

        attack();
    }

    //                                       mechanics

    private boolean crit(){
            return rand.nextInt(100)<getCrit();
    }

    private void attack(){
        double dmg = 7+rand.nextInt(10);
        if (crit()) dmg *= 1.5;
        this.dmg=dmg;
    }

    //heavy attack cost 1 AP, if ap insufficient - do attack() instead
    private void heavyAttack(){
        if (getAp()>=1){
        double dmg = 16+rand.nextInt(10);
        if (crit()) dmg *= 1.5;
        this.dmg=dmg;
        ap--;
        }
        else{
            System.out.println("Fatigued heavy attack!");
            attack();
        }

    }

    private void block() {
        if (getAp()>=1){
            System.out.println(getName()+" blocks!");
            isBlocking=true;
            blockCharges+=2;
            ap--;
        }
    }

    //special - recovers 50% of the health lost to the last hit taken

    private void special() {
        if (getAp() >= 2) {
            setHp(getHp() + (lastHit * 0.5));
            ap--;
            System.out.println(getClass().getSimpleName() + " has used Recovery!");
        }
    }

    //                           functional

    public double dmgDone() {
        return dmg;
    }

    public void setDmg(double dmg){
        this.dmg=dmg;
    }

    private void dmgIntake(Combat player) {
        double temp = player.dmgDone();
        boolean run = true;

        while (run) {
            if (isBlocking) temp /= 2;
            setHp(getHp() - temp);
            if (checkLife()) {
                System.out.println(this.name + " (" + getDifficulty() + ") took " + temp + " dmg");
                System.out.println("Enemy (" + getDifficulty() + ") slain, proceeding to next round!");
                break;
            }
            lastHit = temp;
            System.out.println();
            System.out.println(this.name + " (" + getDifficulty() + ") took " + temp + " dmg");
            System.out.println(getHp() + " hp: " + this.getAp() + " ap " + getBlockCharges() + " blocks left" + "\n_____________");
            if (isBlocking&&blockCharges>=1) blockCharges--;
            break;
        }
    }

    public boolean checkLife() {
            return getHp() <= 0;
    }

    public void getDmgIntake(Combat player){
        dmgIntake(player);
    }



}
