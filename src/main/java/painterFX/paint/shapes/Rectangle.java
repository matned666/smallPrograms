package painterFX.paint.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Shape {

    private  double startX;
    private  double startY;
    private  double endX;
    private  double endY;
    private  double startX_FOR_SAVE;
    private  double startY_FOR_SAVE;
    private  double endX_FOR_SAVE;
    private  double endY_FOR_SAVE;

    public Rectangle(double startX, double startY, double endX, double endY) {
        this.startX = Math.min(startX,endX);
        this.startY = Math.min(startY,endY);
        this.endX = Math.abs(startX-endX);
        this.endY = Math.abs(startY-endY);
        startX_FOR_SAVE = startX;
        startY_FOR_SAVE = startY;
        endX_FOR_SAVE = endX;
        endY_FOR_SAVE = endY;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.fillRect(startX,startY,endX,endY);
        context.strokeRect(startX,startY,endX,endY);
    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Rectangle;");
        builder.append(startX_FOR_SAVE).append(";");
        builder.append(startY_FOR_SAVE).append(";");
        builder.append(endX_FOR_SAVE).append(";");
        builder.append(endY_FOR_SAVE).append(";");
        builder.append(fillColor).append(";");
        builder.append(strokeColor).append(";");
        return builder.toString();
    }

}
