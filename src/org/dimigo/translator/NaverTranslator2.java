package org.dimigo.translator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class NaverTranslator2 {

    public static String translate(String input) {  // 네이버 번역 (Papago SMT) API
        String clientId = "sIfV2yQfpxQTr81U_zfR";
        String clientSecret = "cVEkyyKG03";
        String result = "";

        try {
            String text = URLEncoder.encode(input, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

                String postParams = "source=en&target=ko&text=" + text; //영어를 한국어로

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());

            result = parseJson(response.toString());

        }catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    private static String parseJson(String json) throws Exception {
        Map<String, Object> map = new ObjectMapper().readValue(json, Map.class);
        Map<String, Object> message = (Map<String, Object>) map.get("message");
        Map<String, String> result = (Map<String, String>) message.get("result");

        return result.get("translatedText");

    }
}