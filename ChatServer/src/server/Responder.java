package server;


import java.nio.ByteBuffer;

public abstract class Responder {

    public abstract void run();

    protected abstract ByteBuffer makeResponse();
}
