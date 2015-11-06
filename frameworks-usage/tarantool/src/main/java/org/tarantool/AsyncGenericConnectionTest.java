package org.tarantool;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncGenericConnectionTest {
    public static void main(String[] args) {
        try {
            TarantoolSelectorWorker worker = new TarantoolSelectorWorker() {
                @Override
                public void error(SelectionKey key, Exception e) {
                    e.printStackTrace();
                }
            };
            ExecutorService exec = Executors.newFixedThreadPool(16);
            Thread thread = new Thread(worker);
            thread.start();
            final TarantoolAsyncConnection16 con = new TarantoolAsyncConnection16Impl(worker, SocketChannel.open(new InetSocketAddress("localhost", 3301)), "test", "test", 100, TimeUnit.MILLISECONDS);
            for (int i = 0; i < 16; i++) {
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 10; i++) {
                            if (con.isValid()) {
                                Future<List> call = con.call("math.ceil", 1.3);
                                Future<List> eval = con.eval("return ...", 1, 2, 3);
                                System.out.println(call.isDone() + " " + eval.isDone());
                                try {
                                    List cr = call.get();
                                    List er = eval.get();
                                    System.out.println(cr);
                                    System.out.println(er);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("Connection is not valid");
                            }

                        }
                    }
                });

            }
            exec.shutdown();
            exec.awaitTermination(1, TimeUnit.SECONDS);
            con.close();
            thread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
