package painterFX.paint.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Triangle extends Shape {

    private  double startX;
    private  double startY;
    private  double midX;
    private  double midY;
    private  double endX;
    private  double endY;
    private  double startX_FOR_SAVE;
    private  double startY_FOR_SAVE;
    private  double endX_FOR_SAVE;
    private  double endY_FOR_SAVE;

    public Triangle(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = startY;
        midX = startX+((endX-startX)/2);
        midY = endY;
        startX_FOR_SAVE = startX;
        startY_FOR_SAVE = startY;
        endX_FOR_SAVE = endX;
        endY_FOR_SAVE = endY;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.beginPath();
        context.lineTo(startX,startY);
        context.lineTo(midX,midY);
        context.lineTo(endX,endY);
        context.lineTo(startX,startY);

        context.closePath();
        context.stroke();
        context.fill();
    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Triangle;");
        builder.append(startX_FOR_SAVE).append(";");
        builder.append(startY_FOR_SAVE).append(";");
        builder.append(endX_FOR_SAVE).append(";");
        builder.append(endY_FOR_SAVE).append(";");
        builder.append(fillColor).append(";");
        builder.append(strokeColor).append(";");
        return builder.toString();
    }
}
