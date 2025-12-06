package weapons.model;

import java.util.ArrayList;
import java.util.List;

public class Knife {
    private String id;
    private String type;
    private String handy;
    private String origin;
    private List<Visual> visuals;
    private boolean value;

    public Knife() {
        this.visuals = new ArrayList<>();
    }

    public Knife(String id, String type, String handy, String origin, boolean value) {
        this.id = id;
        this.type = type;
        this.handy = handy;
        this.origin = origin;
        this.value = value;
        this.visuals = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHandy() {
        return handy;
    }

    public void setHandy(String handy) {
        this.handy = handy;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public List<Visual> getVisuals() {
        return visuals;
    }

    public void setVisuals(List<Visual> visuals) {
        this.visuals = visuals;
    }

    public void addVisual(Visual visual) {
        this.visuals.add(visual);
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Knife{id='" + id + "', type='" + type + "', handy='" + handy + 
               "', origin='" + origin + "', visuals=" + visuals + ", value=" + value + "}";
    }
}

