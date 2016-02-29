//package ru.atconsulting.libs.auth.session;
//
//import com.google.common.base.Preconditions;
//import ru.vimpelcom.bastida.mconfig.impl.SimpleMutableConfig;
//import ru.vimpelcom.bastida.processingscope.BeanTemplate;
//import ru.vimpelcom.bastida.processingscope.ProcessingScope;
//import ru.vimpelcom.uhc.Uhc;
//import ru.vimpelcom.uhc.ning.UhcNing;
//import ru.vimpelcom.util.misc.RpcException;

import java.io.IOError;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Test {

//    public static class Cli implements AuthClient<String> {
//
//        public static class Template implements BeanTemplate<AuthClient<String>> {
//
//            private final Uhc httpClient;
//
//            public Template(Uhc uhc) {
//                this.httpClient = uhc;
//            }
//
//            @Override
//            public Cli create(ProcessingScope procScope) {
//                return new Cli(this, procScope);
//            }
//        }
//
//        private final Template t;
//        private final ProcessingScope procScope;
//
//        Cli(Template t, ProcessingScope processingScope) {
//            this.t = t;
//            this.procScope = processingScope;
//        }
//
//        @Override
//        public CompletableFuture<String> authenticate() {
//            Preconditions.checkNotNull(t.httpClient, "Http client is null");
//
//            procScope.timings.takeReadings("In Cli authenticate");
//            CompletableFuture<String> tokenFuture = new CompletableFuture<>();
//
//            t.httpClient.executeAsync(b -> b.get()
//                    .path("http://localhost:1590/test")
//                    .onCompleted(r -> procScope.timings.takeReadings("Login request completed"))
//                    .onCode(200, r -> {
//                        tokenFuture.complete(String.valueOf(System.nanoTime()));
//                    })
//                    .onCode(403, r -> tokenFuture.completeExceptionally(new AuthenticationFailedException("Couldn't authenticate")))
//                    .onOtherCodes(r -> tokenFuture.completeExceptionally(new RpcException("cli",
//                            String.format("Couldn't send " +
//                                    "message to bauth. Response status: %s", r.getStatus()))))
//                    .onBackendError(t -> tokenFuture.completeExceptionally(new RpcException("cli",
//                            t))));
//            return tokenFuture;
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        SimpleMutableConfig mc = new SimpleMutableConfig(SimpleMutableConfig.empty);
//        mc.set("request-timeout", "100 ms");
//        Cli.Template t = new Cli.Template(new UhcNing(mc));
//
//        SimpleMutableConfig tcmc = new SimpleMutableConfig(SimpleMutableConfig.empty);
//        tcmc.set("timings-context", "tok container");
//        tcmc.set("timings-threshold", "1 ms");
//        TokenContainer<String> tc = new TokenContainer<>(t, tcmc);
//
//        SimpleMutableConfig asc = new SimpleMutableConfig(SimpleMutableConfig.empty);
//
//        PrivilegedSession<String> privilegedSession = new PrivilegedSession<>(tc,
//                new DefaultPrivilegedOperationStrategy<>(), asc);
//
//
//        for (int i = 0; i < 5;  i++) {
//            privilegedSession.run(tok -> {
//                return CompletableFuture.supplyAsync(() -> {
//                    System.out.println(tok);
//                    return "pop with token: " + tok;
//                });
//            }, "none");
//        }
//
//    }
//

}
