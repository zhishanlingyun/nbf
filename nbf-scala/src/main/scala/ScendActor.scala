import scala.actors.Actor

/**
 * Created by Administrator on 2016/2/27 0027.
 */
object ScendActor extends Actor{
  override def act(): Unit = {
    for(i <- 1 to 5){
      println("ScendActor "+i);
      Thread.sleep(1000);
    }
  }
}