package com.henvealf.learn.scala.fromspark


private[fromspark] sealed trait InboxMessage

private[fromspark] case class RpcMessage(str: String) extends InboxMessage
private[fromspark] case object OnStart extends InboxMessage
private[fromspark] case object OnStop extends InboxMessage


/**
  * @author hongliang.yin/Henvealf on 2018/9/3
  */
class InBox(isSafe: Boolean) {

  inbox =>

  private var stopped = false

  private var numActiveThreads:Int = 0
  private var enableConcurrent = false

  protected val messages = new java.util.LinkedList[InboxMessage]()

  inbox.synchronized {
    messages.add(OnStart)
  }


  /**
    * Process stored messages.
    */
  def process(): Unit = {
    var message: InboxMessage = null
    inbox.synchronized {
      // EnableConcurrent 为 false 说明还没有启动 endPoint。
      // 检查状态，开启并发时， 活跃的thread数只能为 0
      // 开启了同步，或者活跃的线程数为0的时候才准许操作。
      // enableConcurrenr || numActiveThreads == 0
      if (!enableConcurrent && numActiveThreads != 0) {
        return
      }
      message = messages.poll()
      if (message != null) {
        numActiveThreads += 1
      } else {
        return
      }
    }
    while (true) {
      message match {
        case RpcMessage(content) =>
          println(s"rpc content is $content")

        case OnStart =>
          if (isSafe) {
            inbox.synchronized {
              if (!stopped) {
                enableConcurrent = true
              }
            }
          }

        case OnStop =>
          val activeThreads = inbox.synchronized { inbox.numActiveThreads }
          assert(activeThreads == 1,
            s"There should be only a single active thread but found $activeThreads threads.")
          assert(isEmpty, "OnStop should be the last message")

      }

      inbox.synchronized {
        // "enableConcurrent" will be set to false after `onStop` is called, so we should check it
        // every time.
        if (!enableConcurrent && numActiveThreads != 1) {
          // If we are not the only one worker, exit
          numActiveThreads -= 1
          return
        }

        message = messages.poll()
        if (message == null) {
          numActiveThreads -= 1
          // return 会直接退出 while 循环
          return
        }
      }
    }

  }

  def post(message: InboxMessage): Unit = inbox.synchronized {
    if (stopped) {
      // We already put "OnStop" into "messages", so we should drop further messages
      onDrop(message)
    } else {
      messages.add(message)
      false
    }
  }


  def stop(): Unit = inbox.synchronized {
    // The following codes should be in `synchronized` so that we can make sure "OnStop" is the last
    // message
    if (!stopped) {
      // We should disable concurrent here. Then when RpcEndpoint.onStop is called, it's the only
      // thread that is processing messages. So `RpcEndpoint.onStop` can release its resources
      // safely.
      enableConcurrent = false
      stopped = true
      messages.add(OnStop)
      // Note: The concurrent events in messages will be processed one by one.
    }
  }

  protected def onDrop(message: InboxMessage): Unit = {
    println("drop message!")
  }

  def isEmpty: Boolean = inbox.synchronized { messages.isEmpty }

}

object InBoxTest {

  def main(args: Array[String]): Unit = {

    val inbox = new InBox(false)

    inbox.post(RpcMessage("Hello you"))
    inbox.post(RpcMessage("Hello me"))
    inbox.post(RpcMessage("Hello world"))
    inbox.post(OnStop)

    inbox.process()
//    new Thread(new Runnable {
//      override def run(): Unit = inbox.process()
//    }).run()
//    Thread.sleep(1000)
//    new Thread(new Runnable {
//      override def run(): Unit = {
//        inbox.post(RpcMessage("Hello you"))
//        inbox.post(RpcMessage("Hello me"))
//        inbox.post(RpcMessage("Hello world"))
//
//        inbox.post(OnStop)
//      }
//    }).run()


  }

}
