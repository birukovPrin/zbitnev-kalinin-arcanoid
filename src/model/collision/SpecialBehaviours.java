package model.collision;

import java.util.ArrayList;

public class SpecialBehaviours {

    /**
     * Если флаг установлен, то поведение применяется не только при столкновении с объектом данного класса, но и его потомками.
     */
    public boolean _flagCheckDerived = false;
    
    public ArrayList<CollisionBehaviour> _behaviours = new ArrayList<>();
    
    public SpecialBehaviours() {
        
    }
    
    public SpecialBehaviours(CollisionBehaviour b) {
        
        if (b == null) {
            throw new NullPointerException();
        }
        _behaviours.add(b);
    }
}
