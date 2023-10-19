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
        while (round){
            player.playerInput();
            checkExchange();
            enemy.decisionMake();
            checkExchange();
            System.out.println("round over_____________");
            }
        }



    private void checkExchange(){
        if (player.dmgDone()!=0){
            enemy.dmgTaken(player);
            player.setDmg(0);
            if (enemy.checkLife()){
                round=false;
            }
        }
        if (enemy.dmgDone()!=0){
            player.dmgTaken(enemy);
            enemy.setDmg(0);
            if (player.checkLife()){
                round=false;
            }
        }
    }



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
