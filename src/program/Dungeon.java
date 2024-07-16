package program;


import program.charclasses.Player;

import java.io.IOException;

public class Dungeon {
    private Player player;
    private Enemy enemy;

    private int roundsWon;

    public Dungeon(Player player) {
        this.player = player;
        this.enemy = new Enemy();
    }

    boolean round=true;
    //                                  Gameplay loop
    protected void startGame(Player player) throws IOException {
  //      Enemy enemy = new Enemy();
        player.setHp(100);
        loop:
        while (round){
            player.playerInput();
            checkExchange();
            enemy.decisionMake();
            checkExchange();
            System.out.println("****** round over ******");
            }
        }



    private void checkExchange(){
        if (player.dmgDone()!=0){
            enemy.getDmgIntake(player);
            player.setDmg(0);
            if (enemy.checkLife()){
                round=false;
                return;
            }
        }
        if (enemy.dmgDone()!=0){
            player.getDmgIntake(enemy);
            enemy.setDmg(0);
            if (player.checkLife()){
                round=false;
            }
        }
    }


//    private void dmgIntake(Combat combat) {
//        double damush = combat.dmgDone();
//
//        if (enemy.isBlocking) damush/=2;
//        enemy.setHp(enemy.getHp()-damush);
//        if (enemy.checkLife()){
//            System.out.println(enemy.getName()+" ("+enemy.getDifficulty()+") took "+damush+" dmg");
//            System.out.println("Enemy ("+enemy.getDifficulty()+") slain, proceeding to next round!");
//            return;
//        }
//        enemy.lastHit=damush;
//        System.out.println();
//        System.out.println(enemy.getName()+" ("+enemy.getDifficulty()+") took "+damush+" dmg");
//        System.out.println(enemy.getHp()+" hp: "+this.enemy.getAp()+" ap");
//        enemy.isBlocking=false;
//    }

   // private void checkLife() throws IOException {
   //     if (enemy.getHp()<=0){
   //         System.out.println("Enemy slain, proceeding to next round!");
   //         roundsWon++;
   //         round=false;
//
   //     }
   //     if(player.getHp()<=0){
   //         System.out.println("You have been slain, game over!");
   //         System.out.println("You survived "+roundsWon+" battles");
   //         round=false;
   //     }
  //  }
}
