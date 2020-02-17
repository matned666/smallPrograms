package painterFX.paint.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Ellipse  extends Shape{

    private  double startX;
    private  double startY;
    private  double endX;
    private  double endY;
    private  double startX_FOR_SAVE;
    private  double startY_FOR_SAVE;
    private  double endX_FOR_SAVE;
    private  double endY_FOR_SAVE;

    public Ellipse(double startX, double startY, double endX, double endY) {
        this.startX = Math.min(startX,endX);
        this.startY = Math.min(startY,endY);
        this.endX = Math.abs(endX-startX);
        this.endY = Math.abs(endY-startY);
        startX_FOR_SAVE = startX;
        startY_FOR_SAVE = startY;
        endX_FOR_SAVE = endX;
        endY_FOR_SAVE = endY;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.fillOval(startX,startY,endX,endY);
        context.strokeOval(startX,startY,endX,endY);
    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Ellipse;");
        builder.append(startX_FOR_SAVE).append(";");
        builder.append(startY_FOR_SAVE).append(";");
        builder.append(endX_FOR_SAVE).append(";");
        builder.append(endY_FOR_SAVE).append(";");
        builder.append(fillColor).append(";");
        builder.append(strokeColor).append(";");
        return builder.toString();
    }
}
