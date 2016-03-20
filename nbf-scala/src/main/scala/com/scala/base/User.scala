package com.scala.base

import scala.beans.BeanProperty

/**
 * Created by Administrator on 2016/3/12 0012.
 */
class User {

  @BeanProperty
  var id:Long = _;

  @BeanProperty
  var name:String = _;

  def this(id:Long,name:String){
    this();
    this.id = id;
    this.name = name;
  }

}
