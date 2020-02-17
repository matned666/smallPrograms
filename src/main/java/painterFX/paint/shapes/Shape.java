package painterFX.paint.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public abstract class Shape {
    protected Paint fillColor;
    protected Paint strokeColor;

    public void drawShape(GraphicsContext context){
        context.setStroke(strokeColor);
        context.setFill(fillColor);
        draw(context);
    }

    public abstract void draw(GraphicsContext context);

    public abstract String getData();

    public void setStrokeColor(Paint strokeColor){
        this.strokeColor = strokeColor;
    }

    public void setFillColor(Paint fillColor){
        this.fillColor = fillColor;
    }



}
