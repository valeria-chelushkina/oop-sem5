package weapons.model;

import java.util.ArrayList;
import java.util.List;

public class KnifeCollection {
    private List<Knife> knives;

    public KnifeCollection() {
        this.knives = new ArrayList<>();
    }

    public List<Knife> getKnives() {
        return knives;
    }

    public void setKnives(List<Knife> knives) {
        this.knives = knives;
    }

    public void addKnife(Knife knife) {
        this.knives.add(knife);
    }
}

