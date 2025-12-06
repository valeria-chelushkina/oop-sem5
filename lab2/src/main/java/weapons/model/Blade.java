package weapons.model;

import java.math.BigDecimal;

public class Blade {
    private BigDecimal length;
    private BigDecimal width;

    public Blade() {
    }

    public Blade(BigDecimal length, BigDecimal width) {
        this.length = length;
        this.width = width;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Blade{length=" + length + "cm, width=" + width + "mm}";
    }
}

