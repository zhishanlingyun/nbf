package com.scala.obj.abs

/**
 * Created by Administrator on 2016/4/10 0010.
 */
abstract class Person {

  def id : Int

  override def toString = getClass.getSimpleName+" id = "+id+", ";

}
