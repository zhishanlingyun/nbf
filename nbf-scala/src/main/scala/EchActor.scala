import scala.actors.Actor

/**
 * Created by Administrator on 2016/2/27 0027.
 */
object EchActor extends Actor{
  override def act(): Unit = {
    while(true){
      receive{
        case msg=>
          println("receive msg, msg is "+msg);
      }
    }
  }
}
