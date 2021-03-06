package com.leaf.example.demo.basic;

import com.leaf.common.UnresolvedAddress;
import com.leaf.example.demo.HelloService;
import com.leaf.rpc.DefaultProxyFactory;
import com.leaf.rpc.consumer.DefaultLeafClient;
import com.leaf.rpc.consumer.LeafClient;

public class ConsumerExample {

    private static HelloService helloService;

    public static void main(String[] args) {
        LeafClient leafClient = new DefaultLeafClient("consumer");

        helloService = DefaultProxyFactory.factory(HelloService.class)
                .consumer(leafClient)
                .providers(new UnresolvedAddress("127.0.0.1", 9180))
                .group("test")
                .version("1.0.0")
                .timeMillis(3000L)
                .newProxy();

        new ConsumerExample().invoke();
    }

    public void invoke() {
        String s = helloService.sayHello(" biu biu biu!!!");
        System.out.println(s);
    }
}
