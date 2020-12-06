package cn.it.test;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.junit.Test;

/**
 * @author HHF
 * @date 2020/12/6 - 12:09
 * @describe
 */
public class ZookeeperApiTest {
    /**
     * znodeApi的增删改练习摸板
     */
    @Test
    public void Api_0Test() throws Exception {
        /**
         *  RetryPolicy： 失败的重试策略的公共接口
         *  ExponentialBackoffRetry是 公共接口的其中一个实现类
         *      参数1： 初始化sleep的时间，用于计算之后的每次重试的sleep时间
         *      参数2：最大重试次数
         参数3（可以省略）：最大sleep时间，如果上述的当前sleep计算出来比这个大，那么sleep用这个时间
         */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3,10);

        //创建客户端（通过对应的工厂静态方法）
        /**
         * 参数1：连接的ip地址和端口号
         * 参数2：会话超时时间，单位毫秒
         * 参数3：连接超时时间，单位毫秒
         * 参数4：失败重试策略
         */
        CuratorFramework client =  CuratorFrameworkFactory.newClient("127.0.0.1:2181",3000,1000,retryPolicy);

        //开启客户端(会阻塞到会话连接成功为止)
        client.start();

        /**
         * 创建节点
         */
        //1. 创建一个空节点(a)（只能创建一层节点）
        // client.create().forPath("/a");
        //2. 创建一个有内容的b节点（只能创建一层节点）
        // client.create().forPath("/b", "这是b节点的内容".getBytes());
        //3. 创建多层节点
        // （creatingParentsIfNeeded）是否需要递归创建节点
        // withMode(CreateMode.PERSISTENT)  创建持久性 b节点
        // client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/g");
        //4. 创建带有的序号的节点
        //  client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/e");
        //5. 创建临时节点（客户端关闭，节点消失），设置延时5秒关闭
        // client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/f");
        //6. 创建临时带序号节点（客户端关闭，节点消失），设置延时5秒关闭
        client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/f");
        Thread.sleep(5000);
//关闭客户端
        client.close();
    }

    /**
     * znodeApi的创建练习
     */
    @Test
    public void Api_Create_Test() throws Exception {
        //1、创建失败重试策略接口
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3,3000);

        //2、创建客户端（通过工厂）
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);

        //3、开启客户端(会阻塞到会话连接成功为止)
        client.start();

        /**
         * 4、创建节点
         */
        //1. 创建一个空节点(a)（只能创建一层节点）
        // client.create().forPath("/a");

        //2. 创建一个有内容的b节点（只能创建一层节点）
        // client.create().forPath("/b", "这是b节点的内容".getBytes());

        //3. 创建多层节点
        // （creatingParentsIfNeeded）是否需要递归创建节点
        // withMode(CreateMode.PERSISTENT)  创建持久性 b节点
        // client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/g");

        //4. 创建带有的序号的节点
        //  client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/e");

        //5. 创建临时节点（客户端关闭，节点消失），设置延时5秒关闭
        // client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/f");

        //6. 创建临时带序号节点（客户端关闭，节点消失），设置延时5秒关闭
        //client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/f");
        //Thread.sleep(5000);

        //5、关闭客户端
        client.close();
    }


}
