import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class apiParse {
    public static void main(String[] args) {
        try {
            String key = "EeQ8yv6SwhuXSd60XpJqUhtzmlcoYvZuaXMnN0Fw3rMyHD%2FlkxK7CKMA4KCzLfYPz2Pc%2B4XPgsQki831XrQkHg%3D%3D"; /*인증키*/
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


            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(conn.getInputStream());

            doc.getDocumentElement().normalize();


            NodeList nList = doc.getElementsByTagName("itemList");

            for(int i=0; i<nList.getLength();i++){
                Node nNode = nList.item(i);

                Element eElement = (Element) nNode;

                System.out.println("ARRIVALESTIMATETIME : "+getTagValue("ARRIVALESTIMATETIME",eElement));
                System.out.println("BSTOPID : "+getTagValue("BSTOPID",eElement));
                System.out.println("BUSID : "+getTagValue("BUSID",eElement));
                System.out.println("BUS_NUM_PLATE : "+getTagValue("BUS_NUM_PLATE",eElement));
                System.out.println("CONGESTION : "+getTagValue("CONGESTION",eElement));
                System.out.println("DIRCD : "+getTagValue("DIRCD",eElement));
                System.out.println("LASTBUSYN : "+getTagValue("LASTBUSYN",eElement));
                System.out.println("LATEST_STOP_ID : "+getTagValue("LATEST_STOP_ID",eElement));
                System.out.println("LATEST_STOP_NAME : "+getTagValue("LATEST_STOP_NAME",eElement));
                System.out.println("LOW_TP_CD : "+getTagValue("LOW_TP_CD",eElement));
                System.out.println("REMAIND_SEAT : "+getTagValue("REMAIND_SEAT",eElement));
                System.out.println("REST_STOP_COUNT : "+getTagValue("REST_STOP_COUNT",eElement));
                System.out.println("ROUTEID : "+getTagValue("ROUTEID",eElement));
                System.out.println("==============================================");



            }



        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getTagValue(String tag, String chilTag, Element eElement){
        String result = " ";

        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        for(int i=0; i<eElement.getElementsByTagName(chilTag).getLength(); i++){

            result += nList.item(i).getChildNodes().item(0).getTextContent()+" ";
        }


        return result;
    }

    public static String getTagValue(String tag, Element eElement){
        String result = " ";

        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        result = nList.item(0).getTextContent();

        return result;
    }

}
