package com.nbf.lambda;

import akka.actor.UntypedActor;

/**
 * Created by Administrator on 2016/4/25 0025.
 */
public class Greeter extends UntypedActor {
    public static enum Msg {
        GREET, DONE;
    }
    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg == Msg.GREET) {
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else unhandled(msg);
    }
}
