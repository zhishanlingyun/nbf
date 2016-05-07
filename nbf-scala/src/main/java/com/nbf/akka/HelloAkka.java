package com.nbf.akka;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import com.nbf.lambda.Greeter;
import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2016/4/25 0025.
 */
public class HelloAkka extends UntypedActor {

    Logger log = Logger.getLogger(HelloAkka.class);

    @Override
    public void preStart() throws Exception {
        super.preStart();
        log.info("@@@@@@@preStart@@@@@@");
        final ActorRef greeter =
                getContext().actorOf(Props.create(Greeter.class), "greeter");//创建greeter actor实例
        greeter.tell(Greeter.Msg.GREET, getSelf());//通过tell方法给greeter actor 发送一条消息
    }


    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg == Greeter.Msg.DONE) {
            // when the greeter is done, stop this actor and with it the application
            getContext().stop(getSelf());
        } else unhandled(msg);
    }
}
