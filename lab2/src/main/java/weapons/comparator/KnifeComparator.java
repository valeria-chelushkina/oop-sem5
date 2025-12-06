package weapons.comparator;

import weapons.model.Knife;

import java.util.Comparator;

public class KnifeComparator implements Comparator<Knife> {
    
    @Override
    public int compare(Knife k1, Knife k2) {
        int typeCompare = k1.getType().compareTo(k2.getType());
        if (typeCompare != 0) {
            return typeCompare;
        }
        
        int originCompare = k1.getOrigin().compareTo(k2.getOrigin());
        if (originCompare != 0) {
            return originCompare;
        }
        
        return k1.getId().compareTo(k2.getId());
    }
}

