package day12;

public class Moon {

    private int[] pos;
    private int[] vel;

    public Moon(int[] pos, int[] vel) {
        if (pos.length == 3 && vel.length == 3) {
            this.pos = pos;
            this.vel = vel;
        } else {
            throw new IllegalArgumentException("Wrong moon input");
        }
    }

    public int[] getPos() {
        return pos;
    }

    public int[] getVel() {
        return vel;
    }

    public void setPos(int[] pos) {
        if (pos.length == 3) {
            this.pos = pos;
        } else {
            throw new IllegalArgumentException("Wrong moon input");
        }
    }

    public void setVel(int[] vel) {
        if (vel.length == 3) {
            this.vel = vel;
        } else {
            throw new IllegalArgumentException("Wrong moon input");
        }
    }

    public Moon getCopy() {
        return new Moon (new int[] {pos[0], pos[1], pos[2]}, new int[] {vel[0], vel[1], vel[2]});
    }
}
