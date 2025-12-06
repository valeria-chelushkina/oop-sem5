package weapons.model;

public class Visual {
    private Blade blade;
    private String material;
    private Handle handle;
    private boolean bloodGroove;

    public Visual() {
    }

    public Visual(Blade blade, String material, Handle handle, boolean bloodGroove) {
        this.blade = blade;
        this.material = material;
        this.handle = handle;
        this.bloodGroove = bloodGroove;
    }

    public Blade getBlade() {
        return blade;
    }

    public void setBlade(Blade blade) {
        this.blade = blade;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Handle getHandle() {
        return handle;
    }

    public void setHandle(Handle handle) {
        this.handle = handle;
    }

    public boolean isBloodGroove() {
        return bloodGroove;
    }

    public void setBloodGroove(boolean bloodGroove) {
        this.bloodGroove = bloodGroove;
    }

    @Override
    public String toString() {
        return "Visual{blade=" + blade + ", material=" + material + 
               ", handle=" + handle + ", bloodGroove=" + bloodGroove + "}";
    }
}

