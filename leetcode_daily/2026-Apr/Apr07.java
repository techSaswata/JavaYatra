class Robot {
    private int w;
    private int h;
    private int p;
    private int p1;
    private int p2;
    private int p3;
    private int perim;
    private boolean moved;

    public Robot(int width, int height) {
        this.w = width;
        this.h = height;
        this.p = 0;
        this.moved = false;
        this.p1 = w;
        this.p2 = w + h - 1;
        this.p3 = 2 * w + h - 2;
        this.perim = 2 * w + 2 * h - 4;
    }
    
    public void step(int num) {
        this.moved = true;
        this.p = (this.p + num) % this.perim;
    }
    
    public int[] getPos() {
        if (p < p1) return new int[]{p, 0};
        if (p < p2) return new int[]{w - 1, p - p1 + 1};
        if (p < p3) return new int[]{p3 - 1 - p, h - 1};
        return new int[]{0, perim - p};
    }
    
    public String getDir() {
        if (p == 0) return moved ? "South" : "East";
        if (p < p1) return "East";
        if (p < p2) return "North";
        if (p < p3) return "West";
        return "South";
    }
}