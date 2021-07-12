public class WateringSystem implements Turn{
    int capacity;
    int max_time;
    int time;
    boolean well;
    final int MAX_CAPACITY = 5;

    //singleton design
    private static WateringSystem instance = null;
    private WateringSystem() {
        capacity = MAX_CAPACITY;
        max_time = 3;
        time =0;
        well = false;
    }
    public static WateringSystem getInstanceWateringSystem() {
        if(instance == null) {
            instance = new WateringSystem();
        }
        return instance;
    }

    @Override
    public void turn() {
        if (well)
        time++;
    }
}
