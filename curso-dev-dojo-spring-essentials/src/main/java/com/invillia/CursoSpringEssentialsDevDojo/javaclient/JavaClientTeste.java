package com.invillia.CursoSpringEssentialsDevDojo.javaclient;

import com.invillia.CursoSpringEssentialsDevDojo.domain.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class JavaClientTeste {

    public static void main(String[] args) {
        HttpURLConnection connection = null;

        BufferedReader reader = null;

        String user = "igor";
        String password = "devdojo";

        try{
            URL url = new URL("http://localhost:8080/v1/protected/students/4");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Authorization", "Basic " + encodeUsernamePassword(user, password));
            System.out.println(encodeUsernamePassword(user, password));//mesmo código do postman
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonSb = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null){
                jsonSb.append(line);
            }

            System.out.println(jsonSb.toString());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(reader);
            if(connection != null){
                connection.disconnect();
            }
        }
    }

    public static String encodeUsernamePassword(String user, String password){
        String userPassword = user+":" + password;

        return new String(Base64.encodeBase64(userPassword.getBytes()));
    }
}
