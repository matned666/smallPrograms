package painterFX.paint.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Shape {

    private  double startX;
    private  double startY;
    private  double endX;
    private  double endY;

    public Line(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.strokeLine(startX,startY,endX,endY);
    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Line;");
        builder.append(startX).append(";");
        builder.append(startY).append(";");
        builder.append(endX).append(";");
        builder.append(endY).append(";");
        builder.append(fillColor).append(";");
        builder.append(strokeColor).append(";");
        return builder.toString();
    }
}
