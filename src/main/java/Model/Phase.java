package Model;

public enum Phase {
    DRAW("draw phase"),
    STANDBY("standby phase"),
    MAIN_ONE("main 1 phase"),
    BATTLE("battle phase"),
    MAIN_TWO("main 2 phase"),
    END("end phase");

    String phase;

    Phase(String phase) {
        this.phase = phase;
    }

    @Override
    public String toString() {
        return "Phase : " + phase;
    }
}
