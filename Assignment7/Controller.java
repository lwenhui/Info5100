public class Controller extends Thread {
    private Device device;
    private Sensor heat;
    private Sensor pressure;
    public Controller(Device device, Sensor heat, Sensor pressure) {
        this.device = device;
        this.heat = heat;
        this.pressure = pressure;
    }
    public void run(){
        device.startup();
        synchronized (device) {
            while (heat.getValue() <= 70 && pressure.getValue() <= 100) {
                try {
                    device.wait();
                } catch (InterruptedException e) {}
                System.out.println( "heat -> " + String.format("%.2f", heat.getValue())
                        + ", pressure -> " + String.format("%.2f", pressure.getValue()));
            }
        }
        device.shutdown();
    }
}

class Device {
    private boolean isShutdown;
    public boolean getIsShutdown() {
        return this.isShutdown;
    }
    public void startup() {
        isShutdown = false;
        System.out.println("Device started");
    }
    public void shutdown() {
        isShutdown = true;
        System.out.println("Device shutting down due to maintenance");
    }
}

class Sensor extends Thread {
    private final Device device;
    private double value;
    public Sensor(Device device) {
        this.device = device;
    }
    public double getValue() {
        return value;
    }
    public void updateValue() {
        this.value += 0.001;
    }
    public void run() {
        while (true) {
            synchronized (device) {
                double oldValue = this.value;
                updateValue();
                if (oldValue != this.value) {
                    device.notify();
                }
                if (device.getIsShutdown() == true) {
                    break;
                }
            }
        }
    }
}
class Root {
    public static void main(String args[]) {
        Device device = new Device();
        Sensor heat = new Sensor(device);
        Sensor pressure = new Sensor(device);

        Controller controller = new Controller(device, heat, pressure);

        controller.start();
        heat.start();
        pressure.start();
    }
}
