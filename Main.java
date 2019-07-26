import java.io.BufferedReader;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
public class Main{

    public static void httpReq() {
        try {
            String url = "http://localhost:9999/write?";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", ": Token ERRXfNjCAsNsJhW7jJUWFotbWR1Q7FovoJ-TAJq6ptbQCHdevjE8AaR2ixX7t496W2okGApamv1Sf3rZNZffuA==");

            String urlParameters = "org=NetBeez&bucket=test&precision=ns";

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            sendData(con, "myMeasurement,tag1=value1,tag2=value2 fieldKey=\"fieldValue\" 1556813561098000000");

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String responseLine;
            StringBuffer response = new StringBuffer();
 
            while ((responseLine = responseReader.readLine()) != null) {
                response.append(responseLine+"\n");
            }
            responseReader.close();

            System.out.println(response.toString());
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Main http = new Main();
        System.out.println("Testing - send Http POST request");

        http.httpReq();

        // for(int i = 0; i<50; i++) {
        //     Data d  = new Data();
        //     String dataRaw = d.toString();
        //     System.out.println(dataRaw);
        //     //send data to influxDB
        //     //call function to make the HTTP request
        // }

    }

    protected static void sendData(HttpURLConnection con, String data) throws IOException {
        DataOutputStream wr = null;
        try {
            wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();
        } catch(IOException exception) {
            System.out.println("exception");
            throw exception;
        }
    }
}
    