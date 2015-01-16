﻿package view;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

import model.IngameObject;
import model.Speed2D;
import model.interaction.GenericEventListener;
import model.interaction.PositionChangeListener;
import model.interaction.SpeedChangeListener;

/**
 * Представление отдельного игрового объекта
 * @author Gregory Zbitnev <zbitnev@hotmail.com>
 *
 */
public class IngameObjectView
		implements PositionChangeListener, SpeedChangeListener {

    protected final IngameObject ingameObject;
    
	protected PublishingSprite sprite = null;
	protected Point2D.Float position = null;
	protected ArrayList<PositionChangeListener> positionListeners = new ArrayList<>();
	protected ArrayList<SpeedChangeListener> speedListeners = new ArrayList<>();
	
	/**
	 * Создает представление объекта на основе его модели и спрайта.
	 * Этот метод автоматически согласует слушателей и связывает спрайт с объектом представления, которому он принадлежит.
	 * @param obj Модель игрового объекта.
	 * @param sprite Спрайт, которым он будет отображен.
	 */
	public IngameObjectView(IngameObject obj, PublishingSprite sprite) {
	    
	    if (sprite == null || obj == null) {
	        throw new NullPointerException();
	    }
	    
	    this.ingameObject = obj;
	    this.sprite       = sprite;
	    this.position     = obj.getPosition();
	    this.sprite.setLocation(position.x, position.y);
	    this.sprite.setObjectView(this);
	    addPositionChangeListener(obj);
	    addSpeedChangeListener(obj);
	    obj.addPositionChangeListener(this);
	    obj.addSpeedChangeListener(this);
	}
	
    /**
     * Необходимо использовать вместо прямого обращения к спрайту.
     * @param timeElapsed Прошедшее время.
     */
    public void update(long timeElapsed) {
        
    	sprite.update(timeElapsed);
    	
    	if (sprite.getX() != this.position.x || sprite.getY() != this.position.y) {
    	    this.position = new Point2D.Float((float)sprite.getX(), (float)sprite.getY());
    	    for (PositionChangeListener l : positionListeners) {
    	        l.positionChanged(this.position);
    	    }
    	}
    	
    	// TODO Сообщаем об изменениях слушателям (модели, то бишь)
    }
    
    public void render(Graphics2D g) {
    	
    	sprite.render(g);
    }
    
	@Override
	public void positionChanged(Point2D.Float newpos) {
		
		// TODO Здесь всё будет далеко не так тривиально
		sprite.setLocation(newpos.x, newpos.y);
	}

	@Override
	public void speedChanged(Speed2D newspeed) {
		
		sprite.setSpeed(newspeed.x(), newspeed.y());
	}
	
	/**
	 * Возвращает модель игрового объекта.
	 * @return IngameObject.
	 */
	public IngameObject getIngameObject() {
	    
	    return ingameObject;
	}
	
	/**
	 * Добавить спрайт, принадлежащий данному представлению объекта
	 * @param sprite Добавляемый спрайт
	 */
	public void setSprite(PublishingSprite sprite) {
		
		if (sprite == null) {
			throw new NullPointerException();
		}
		
		this.sprite = sprite;
	}
	
	/**
	 * Возвращает спрайт, принадлежащий данному представлению объекта.
	 * @return Спрайт.
	 */
	public PublishingSprite getSprite() {
	    return sprite;
	}
	
	/**
	 * Добавить слушателя изменения позиции представления объекта
	 * @param l Новый слушатель
	 */
	public void addPositionChangeListener(PositionChangeListener l) {
		positionListeners.add(l);
	}
	
	/**
	 * Удалить слушателя изменения позиции представления объекта
	 * @param l Удаляемый слушатель
	 */
	public void removePositionChangeListener(PositionChangeListener l) {
		positionListeners.remove(l);
	}
	
	/**
	 * Добавить слушателя изменения скорости представления объекта
	 * @param l Новый слушатель
	 */
	public void addSpeedChangeListener(SpeedChangeListener l) {
		speedListeners.add(l);
	}
	
	/**
	 * Удалить слушателя изменения скорости представления объекта
	 * @param l Удаляемый слушатель
	 */
	public void removeSpeedChangeListener(SpeedChangeListener l) {
		speedListeners.remove(l);
	}
}
