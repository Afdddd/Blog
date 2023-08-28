import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class XML_Parsing {

    public static void main(String[] args) throws IOException {

        String key = "EeQ8yv6SwhuXSd60XpJqUhtzmlcoYvZuaXMnN0Fw3rMyHD%2FlkxK7CKMA4KCzLfYPz2Pc%2B4XPgsQki831XrQkHg%3D%3D";
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/6280000/busArrivalService/getAllRouteBusArrivalList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+key); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("bstopId","UTF-8") + "=" + URLEncoder.encode("165000111", "UTF-8")); /*정류소 고유번호*/
        URL url = new URL(urlBuilder.toString());



        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");
        conn.setRequestProperty("Accept", "*/*;q=0.9");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }

    }

