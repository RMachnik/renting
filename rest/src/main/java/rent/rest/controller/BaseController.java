package rent.rest.controller;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Map;

@Controller
public class BaseController {

    @Autowired
    ConfigurableEnvironment env;

    @RequestMapping("/login")
    String login(Model model) {
        model.addAttribute("ipAddress", getIpAddress());
        return "login";
    }

    @RequestMapping("/")
    String index(Model model) {
        model.addAttribute("ipAddress", getIpAddress());
        return "index";
    }

    @RequestMapping("/conf")
    String conf(Model model) {
        model.addAttribute("ipAddress", getIpAddress());
        model.addAttribute("props", getResolvedProperties());
        return "conf";
    }

    private Map<String, String> getResolvedProperties() {
        Map<String, String> properties = Maps.newTreeMap();
        for (PropertySource<?> propertySource : env.getPropertySources()) {
            if (propertySource instanceof EnumerablePropertySource) {
                for (String name : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                    properties.put(name, env.getProperty(name));
                }
            }
        }
        return properties;
    }

    private String getIpAddress() {
        String ip = "unknown";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {

                NetworkInterface iface = interfaces.nextElement();

                if (iface.isLoopback() || !iface.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                }
            }
        } catch (SocketException ex) {
            throw new RuntimeException(ex);
        }
        return ip;
    }
}
