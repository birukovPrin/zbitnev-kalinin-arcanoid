package model.brick;

import java.awt.Dimension;
import java.awt.geom.Point2D.Float;

import model.GameField;
import model.Speed2D;

/**
 * Модель неразрушаемого кирпича.
 * @author Nikita Kalinin <nixorv@gmail.com>
 *
 */
public class UnbreakableBrick extends Brick {

	public UnbreakableBrick(GameField field, Float pos, Dimension dim, Speed2D speed) {
        
	    super(field, pos, dim, speed);
    }

    public UnbreakableBrick(GameField field, Float pos, Dimension dim) {
        
        super(field, pos, dim);
    }

    public UnbreakableBrick(GameField field) {
		
        super(field);
	}
    
    @Override
    public Object clone() throws CloneNotSupportedException {
    	
    	UnbreakableBrick clone = (UnbreakableBrick) super.clone();
    	return clone;
    }

}
