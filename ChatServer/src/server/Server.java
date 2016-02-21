package server;


public abstract class Server {

    public static int DEFAULT_PORT = 37;

    protected boolean isRunning = true;

    public abstract void run();

    protected abstract void shutdown();

    protected void restart() {
        shutdown();
        run();
    }
}
