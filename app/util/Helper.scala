package util

object Helper{
  def ifTrue(condition:Boolean,trueValue:String,falseValue:String)={
    if(condition) trueValue else falseValue
  }
}
