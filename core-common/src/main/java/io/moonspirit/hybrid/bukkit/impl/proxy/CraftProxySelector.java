package io.moonspirit.hybrid.bukkit.impl.proxy;

import java.io.IOException;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.List;

public class CraftProxySelector extends ProxySelector {

    private final Proxy proxy;

    public CraftProxySelector() {
        this.proxy = Proxy.NO_PROXY;
    }

    public CraftProxySelector(Proxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public List<Proxy> select(URI uri) {
        return Collections.singletonList(proxy);
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
    }
}
