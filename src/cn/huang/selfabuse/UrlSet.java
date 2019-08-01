package selfabuse;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

/**
 * 建议使用URI来去做Set或者Map的key，因为URL对hashCode和equals的实现不是很好，依赖于具体的环境
 * @author Administrator
 *
 */
public class UrlSet {
    
    private static final String[] URL_NAMES = {
      "http://javapuzzlers.com",
      "http://apache2-snort.skybar.dreamhost.com",
      "http://www.google.com",
      "http://javapuzzlers.com",
      "http://findbugs.sourceforge.net",
      "http://www.cs.umd.edu"
    };
    
    public static void main(String[] args) throws URISyntaxException
    {
        Set<URI> favorites = new HashSet<>();
        for(String urlName : URL_NAMES) {
            favorites.add(new URI(urlName));
        }
        System.out.print(favorites.size());
    }
}
