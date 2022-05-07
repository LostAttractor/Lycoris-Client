package rbq.wtf.lycoris.client.utils;

import javax.vecmath.Vector2f;

public class Angles extends Vector2f {
    public Angles(Float x, Float y) {
        super(x, y);
    }

    public Angles setYaw(Float yaw) {
        this.setX(yaw);
        return this;
    }

    public Angles setPitch(Float pitch) {
        this.setY(pitch);
        return this;
    }

    public Float getYaw() {
        return this.getX();
    }

    public Float getPitch() {
        return this.getY();
    }

    public Angles constrantAngle() {
        this.setYaw(this.getYaw() % 360F);
        this.setPitch(this.getPitch() % 360F);
        while (this.getYaw() <= -180F) {
            this.setYaw(this.getYaw() + 360F);
        }
        while (this.getPitch() <= -180F) {
            this.setPitch(this.getPitch() + 360F);
        }
        while (this.getYaw() > 180F) {
            this.setYaw(this.getYaw() - 360F);
        }
        while (this.getPitch() > 180F) {
            this.setPitch(this.getPitch() - 360F);
        }
        return this;
    }
}
