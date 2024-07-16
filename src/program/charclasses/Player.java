package program.charclasses;

import program.Combat;
import program.Entity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Player extends Entity implements Combat {

    private double dmg;
    public Player(String name) {
        super();
        this.name=name;

    }

    BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
    Random rand = new Random();
    private boolean crit(){
        return rand.nextInt(100)<getCrit();
    }

    private void normalAttack(){
        double dmg = 7+rand.nextInt(10);
        if (crit()) dmg *= 1.5;
        this.dmg=dmg;
        ap++;
    }

    private void heavyAttack(){
        double dmg = 16+rand.nextInt(10);
        if (crit()) dmg *= 1.5;
        this.dmg=dmg;
    }


    private void block() {
        if (getAp()>=1){
            isBlocking=true;
            setAp(getAp()-1);
            blockCharges+=2;
        }
    }

    private void special() {
        if (getAp()>=2){
            setHp(getHp()+(lastHit*0.5));
            setAp(getAp()-2);
            System.out.println(getClass().getSimpleName()+" has used Recovery!");
        }
    }

    @Override
    public double dmgDone() {
        return dmg;
    }

    public void setDmg(double dmg){
        this.dmg=dmg;
    }


    private void dmgIntake(Combat enemy) {
      double temp = enemy.dmgDone();
      boolean run =true;

      while(run){
        if (isBlocking) temp/=2;
        setHp(getHp()-temp);
        if (checkLife()){
            System.out.println(getClass().getSimpleName()+" took "+temp+" dmg");
            System.out.println("You have been slain, game over!");
            break;
        }
        lastHit=temp;
        System.out.println(getClass().getSimpleName()+" took "+temp+" dmg");
        System.out.println(getHp()+" hp: "+getAp()+" ap "+getBlockCharges()+" blocks left"+"\n_____________");
        if (isBlocking&&blockCharges>=1) blockCharges--;
        break;
      }
    }

    public void getDmgIntake(Combat enemy){
        dmgIntake(enemy);
    }
    @Override
    public boolean checkLife() {
        return getHp() <= 0;
    }

    public void playerInput() throws IOException {
        System.out.println(this.getName()+"'s turn!\nattack|heavy attack|block|special");
        int activity = Integer.parseInt(reader.readLine());
        switch (activity){
            case 1 -> normalAttack();
            case 2 -> heavyAttack();
            case 3 -> block();
            case 4 -> special();
            default -> {System.out.println("Unknown input! Try again (1-4)");playerInput();}
        }
    }

}
