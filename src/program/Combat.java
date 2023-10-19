package program;

public interface Combat {

    double dmgDone();

    void block();

    void special();

    void dmgTaken(Combat combatDmg);


}
