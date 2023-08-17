import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int siteNum = Integer.parseInt(st.nextToken());
        int findSiteNum = Integer.parseInt(st.nextToken());

        Map<String,String> sites = new HashMap<>();
        for (int i = 0; i < siteNum; i++) {
            st = new StringTokenizer(br.readLine());
            sites.put(st.nextToken(), st.nextToken());
        }

        for (int i = 0; i < findSiteNum; i++) {
            System.out.println(sites.get(br.readLine()));
        }
    }
}
