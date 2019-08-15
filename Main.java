import java.io.BufferedReader;
import java.io.IOException;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
public class Main{

    public static void writeData(String rawData) {
        try {
            String wrUrl = "http://localhost:9999/api/v2/write?org=NetBeez&bucket=Vivian&precision=ms";
            URL obj1 = new URL(wrUrl);
            HttpURLConnection con = (HttpURLConnection) obj1.openConnection();

            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", "Token ERRXfNjCAsNsJhW7jJUWFotbWR1Q7FovoJ-TAJq6ptbQCHdevjE8AaR2ixX7t496W2okGApamv1Sf3rZNZffuA==");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(rawData);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + wrUrl);
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

    public static void queryData(){
        try{
            String queryUrl = "http://localhost:9999/api/v2/query?org=NetBeez";
            URL obj2 = new URL(queryUrl);
            HttpURLConnection connection = (HttpURLConnection) obj2.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Token ERRXfNjCAsNsJhW7jJUWFotbWR1Q7FovoJ-TAJq6ptbQCHdevjE8AaR2ixX7t496W2okGApamv1Sf3rZNZffuA==");
            connection.setRequestProperty("Accept", "application/csv");
            connection.setRequestProperty("Content-Type", "application/vnd.flux");

            connection.setDoOutput(true);
            DataOutputStream query = new DataOutputStream(connection.getOutputStream());
            query.writeBytes("from(bucket:\"Vivian\") |> range(start:-1h, stop:1h) |> max()");
            query.flush();
            query.close();

            int responseCode = connection.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + queryUrl);
            System.out.println("Response Code : " + responseCode);
            
            BufferedReader responseReader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String responseLine;
            StringBuffer response = new StringBuffer();
 
            while ((responseLine = responseReader.readLine()) != null) {
                response.append(responseLine+"\n");
            }
            responseReader.close();

            System.out.println(response.toString());
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter initial id");
        int prevId = Integer.parseInt(br.readLine());
        System.out.println("Enter initial seq");
        int prevSeq = Integer.parseInt(br.readLine());
        System.out.println("Enter initial val");
        int prevVal = Integer.parseInt(br.readLine());
        System.out.println("Enter NetBeez test ID");
        int nbId = Integer.parseInt(br.readLine());
        System.out.println("Enter the number of datapoints");
        int n = Integer.parseInt(br.readLine());

        //Main.queryData();

        for(int i = 0; i<n; i++) {
            Data d  = new Data(prevId+i, prevSeq+i, prevVal+i, nbId, prevSeq+i);
            String dataRaw = d.toString();
            System.out.println(dataRaw);
            Main.writeData(dataRaw);
        }
    }
}
    