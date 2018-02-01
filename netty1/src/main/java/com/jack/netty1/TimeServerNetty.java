package com.jack.netty1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by jack on 2018/1/6.
 * https://www.jianshu.com/p/0bff7c020af2
 * http://blog.csdn.net/u010853261/article/details/53738060
 * https://www.jianshu.com/p/b9f3f6a16911
 * https://www.jianshu.com/p/fd815bd437cd
 */
public class TimeServerNetty {

    public void bind(int port) throws Exception{
        //配置服务端的NIO线程组,专门用于网络事件的处理，这里创建两个的原因是一个用于服务端接收客户端的连接，
        //另外一个用于进行SocketChannel的网络读写
        EventLoopGroup bossGroup = new NioEventLoopGroup();//boss用来接收进来的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();//用来处理已经被接收的连接
        try {
            //创建ServerBootstrap对象，是Netty用于启动NIO服务端的辅助启动类
            ServerBootstrap bootstrap = new ServerBootstrap();
            //将两个NIO线程组当作入参传到ServerBootstrap对象中
            bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,2014)//配置NioServerSocketChannel的TCP参数此处将backlog设置为1024
                .childHandler(new ChildChannelHandler());//绑定IO事件的处理类，主要用于处理网络I/O事件，比如，记录日志，对消息进行编码
            //绑定端口，同步等待成功，返回ChannelFuture，用于异步操作的通知回调
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        } finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        int port = 7010;
        new TimeServerNetty().bind(port);
    }
}
