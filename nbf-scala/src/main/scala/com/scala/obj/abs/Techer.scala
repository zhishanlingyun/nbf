package com.scala.obj.abs

/**
 * Created by Administrator on 2016/4/10 0010.
 */
class Techer(name:String) extends Person{
  override def id: Int = name.hashCode;
}
