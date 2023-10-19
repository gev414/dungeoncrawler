package program;

import program.charclasses.Player;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Player player = new Player("jake");
        Dungeon dungeon = new Dungeon(player);
        dungeon.startGame(player);

    }
}
