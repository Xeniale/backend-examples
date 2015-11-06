package org.tarantool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class TestClient16AsyncWithJackson {
    /*
    Before executing this test you should configure your local tarantool

    box.cfg{listen=3301}
    box.schema.space.create('tester2')
    box.space.tester2:create_index('primary', {type = 'hash', parts = {1, 'NUM'}})

    box.schema.user.create('test', { password = 'test' })
    box.schema.user.grant('test', 'execute,read,write', 'universe')


   */
    public static void main(String[] args)
            throws IOException, ExecutionException, InterruptedException, URISyntaxException {

            TarantoolSelectorWorker worker = new TarantoolSelectorWorker() {

                @Override
                public void error(SelectionKey key, Exception e) {
                    e.printStackTrace();
                }
            };
            ExecutorService exec = Executors.newFixedThreadPool(2);
            Thread thread = new Thread(worker);
            thread.start();
            final TarantoolAsyncGenericConnection16 con = new TarantoolAsyncGenericConnection16Impl(new JacksonMapper(), worker, SocketChannel.open(new InetSocketAddress("192.168.6.134", 3301)), "test", "test", 100, TimeUnit.MILLISECONDS);
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            Future<Pojo> eval = con.eval(Pojo.class, "return 1,99,'hello',false,{'no'},{4,5," + (Math.random() * 100) + "}");

                            System.out.println("before get " + eval.isDone());
                            try {
                                Pojo p = eval.get();
                                System.out.println("Pojo got: " + p);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("after get " + eval.isDone());
                        }
                    }
                });

            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
            con.close();
            thread.interrupt();
    }
}
