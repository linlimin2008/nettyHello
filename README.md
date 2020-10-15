## netty学习
### bio
### nio

####Buffer缓冲区
缓冲区本质上是一个可以写入数据的内存块(类似数组),然后可以再次读取。此内存块包含在 NIO Buffer对象中,该对象提供了一组方法,可以更轻松地使用内存块。
相比较直接对数组的操作, Buffer ApI更加容易操作和管理。

使用 Buffer进行数据写入与读取,需要进行如下四个步骤：
1.将数据写入缓冲区
2.调用buffer.flip(),转换为读取模式
3.缓冲区读取数据
4.调用 buffer.clear()或 buffer.compact()清除线冲区

Buffer三个重要属性:
capacity容量:作为一内存块, Buffer具有一定的固定大小,也称为“容量”。
position位置:写入模式时代表写数据的位置。读取模式时代表读取数据的位置。 
limit限制:写入模式,限制等于buffer的容量。读取模式下, limit等于写入的数据量。

####ByteBuffer内存类型

ByteBuffer为性能关键型代码提供了直接内存(direct堆外)和非直接内存(heap堆)两种实现。 
堆外内存获取的方式: ByteBuffer directByteBuffer =ByteBuffer.allocateDirect(noBytes);

好处: 
1、进行网络10或者文件10时比heapBuffer少一次拷贝。(file/socket ---OS memory---jvm heap) GC会移动对象内存,在写file或socket的过程中, JVM的实现中,会先把数据复制到堆外,再进行写入。
 2、 GC范围之外,降低GC压力,但实现了自动管理。DirectByteBuffer中有一个Cleaner对象 (PhantomReference), Cleaner被GC前会执行clean方法,触发DirectByteBuffer中定义的Deallocator
 
#### Channel通道
#### Selector选择器
Selector是ー个 Java NIO组件,可以检查一个或多个NIO通道,并确定哪些通道已准备好进行读取或写入。实现单个线程可以管理多个通道,从而管理多个网络连接。

一个线程使用 Selector监听多个channel的不同事件:
四个事件分别对应 Selectionkey四个常量。
1.Connect连接( Selectionkey.OP_ CONNECT)
2.Accept准备就绪(OP_ ACCEPT)
3.Read读取(OP_READ) 
4.Write写入(OP_ WRITE)