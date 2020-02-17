package painterFX.paint.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Point extends Shape{

    private  double X;
    private  double Y;


    public Point(double x, double y) {
        X = x;
        Y = y;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.fillRect(X,Y,2,2);
    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Point;");
        builder.append(X).append(";");
        builder.append(Y).append(";");
        builder.append("0").append(";");
        builder.append("0").append(";");
        builder.append(fillColor).append(";");
        builder.append(strokeColor).append(";");
        return builder.toString();
    }
}
