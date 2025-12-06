package weapons.model;

public class Handle {
    private String material;
    private String woodType;

    public Handle() {
    }

    public Handle(String material, String woodType) {
        this.material = material;
        this.woodType = woodType;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getWoodType() {
        return woodType;
    }

    public void setWoodType(String woodType) {
        this.woodType = woodType;
    }

    @Override
    public String toString() {
        if (woodType != null && !woodType.isEmpty()) {
            return "Handle{material=" + material + ", woodType=" + woodType + "}";
        }
        return "Handle{material=" + material + "}";
    }
}

