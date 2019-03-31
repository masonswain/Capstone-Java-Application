/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Joel
 */
public class setWidgetPosition {
    
    public static double setWidgetY(double yCoordinate, Rectangle2D primaryScreenBounds, double nextStageHeight){
        
        double returnedY;
        
        System.out.println("Screen Height: "+primaryScreenBounds.getHeight());
        System.out.println("Screen Y: "+yCoordinate);
        System.out.println("Height: "+nextStageHeight);
        System.out.println("Screen Y + Height + 5 = "+(+nextStageHeight+5));
        
        if((yCoordinate+nextStageHeight+5)>primaryScreenBounds.getHeight()){
            System.out.println("to close to bottom");
            returnedY=(primaryScreenBounds.getHeight() - (nextStageHeight+5));
        }else if((yCoordinate-10)<0){
            System.out.println("to close to top");
            returnedY=(primaryScreenBounds.getMinY()+10);
        }else {
            System.out.println("y not changed");
            returnedY=(yCoordinate);
        }
        
        System.out.println("yCoordinate: "+yCoordinate);
        return returnedY;
        
    }
    
    public static double setWidgetY(MouseEvent event, Rectangle2D primaryScreenBounds, double nextStageHeight){
        
        double yCoordinate;
        System.out.println("Screen Height: "+primaryScreenBounds.getHeight());
        System.out.println("Screen Y: "+event.getScreenY());
        System.out.println("Height: "+nextStageHeight);
        System.out.println("Screen Y + Height + 5 = "+(event.getScreenY()+nextStageHeight+5));
        
        if((event.getScreenY()+nextStageHeight+5)>primaryScreenBounds.getHeight()){
            System.out.println("to close to bottom");
            yCoordinate=(primaryScreenBounds.getHeight() - (nextStageHeight+5));
        }else if((event.getScreenY()-nextStageHeight+10)<0){
            System.out.println("to close to top");
            yCoordinate=(primaryScreenBounds.getMinY()+10);
        }else {
            System.out.println("y not changed");
            yCoordinate=(event.getScreenY());
        }
        
        System.out.println("yCoordinate: "+yCoordinate);
        return yCoordinate;
        
    }
    
}
