package socketport;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.map.MultiValueMap;

public class BruteForce 
{
    
    public void ssh(String ip)
    {
        SSH ssh = new SSH();
        WordList wl = new WordList();
        List list;
        MultiValueMap map = wl.getWordList();
        
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) 
        {
            Map.Entry pair = (Map.Entry)it.next();
            list = (List) map.get(pair.getKey());
            for (int j = 0; j < list.size(); j++) 
            {
                if(ssh.connect(ip, pair.getKey().toString(), list.get(j).toString()))
                {
                    System.out.println("\t"+"Found default credentials : " + pair.getKey().toString() + " / " + list.get(j).toString());
                    return;
                }
            }
            it.remove();
        }
    }
}
