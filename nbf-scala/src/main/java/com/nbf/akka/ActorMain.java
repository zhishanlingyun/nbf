package com.nbf.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.nbf.lambda.Greeter;

public class ActorMain {

    public static void main(String[] args){
        ActorSystem system = ActorSystem.create("actormain");
        ActorRef actor1 = system.actorOf(Props.create(HelloAkka.class));
        ActorRef actor2 = system.actorOf(Props.create(Greeter.class));
        actor2.tell("hello akka",actor1);
    }

}
