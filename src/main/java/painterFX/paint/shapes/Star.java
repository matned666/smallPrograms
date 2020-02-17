package painterFX.paint.shapes;

import javafx.scene.canvas.GraphicsContext;

public class Star extends Shape {

    private  double x1;
    private  double y1;
    private double x2;
    private double y2;
    private double x3;
    private double y3;
    private double x4;
    private double y4;


    private  double x5;
    private  double y5;
    private  double x12;
    private  double y12;
    private double x23;
    private double y23;
    private double x34;
    private double y34;
    private double x45;
    private double y45;
    private  double x51;
    private  double y51;

    public Star(double x1, double y1, double x4, double y4) {
        this.x1 = x1;
        this.y1 = y1;
        this.x4 = x4;
        this.y4 = y4;
        this.x12 = x1+0.42*(x4-x1);
        this.y12 = y1;
        this.x2 = x1+0.58*(x4-x1);
        this.y2 = y1 -0.56*(y4-y1);
        this.x23 = x1+0.75*(x4-x1);
        this.y23 = y1;
        this.x3 = x4+0.17*(x4-x1);
        this.y3 = y1;
        this.x34 = x1+0.83*(x4-x1);
        this.y34 = y1+0.44*(y4-y1);
        this.x45 = x1+0.58*(x4-x1);
        this.y45 = y1+0.70*(y4-y1);
        this.x5 = x1+0.17*(x4-x1);
        this.y5 = y4;
        this.x51 = x1+0.33*(x4-x1);
        this.y51 = y1+0.44*(y4-y1);

    }

    @Override
    public void draw(GraphicsContext context) {
        context.beginPath();
        context.lineTo(x1,y1);
        context.lineTo(x12,y12);
        context.lineTo(x2,y2);
        context.lineTo(x23,y23);
        context.lineTo(x3,y3);
        context.lineTo(x34,y34);
        context.lineTo(x4,y4);
        context.lineTo(x45,y45);
        context.lineTo(x5,y5);
        context.lineTo(x51,y51);
        context.lineTo(x1,y1);
        context.closePath();
        context.stroke();
        context.fill();
    }

    @Override
    public String getData() {
        StringBuilder builder = new StringBuilder();
        builder.append("Star;");
        builder.append(x1).append(";");
        builder.append(y1).append(";");
        builder.append(x4).append(";");
        builder.append(y4).append(";");
        builder.append(fillColor).append(";");
        builder.append(strokeColor).append(";");
        return builder.toString();
    }
}
