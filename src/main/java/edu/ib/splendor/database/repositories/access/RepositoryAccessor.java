package edu.ib.splendor.database.repositories.access;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ib.splendor.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RepositoryAccessor<Dto> {
    private final ObjectMapper mapper;
    private final Class<Dto> clazz;
    private final Class<Dto[]> clazzs;
    private final String urlString;
    public RepositoryAccessor(String sufix, Class<Dto> clazz, Class<Dto[]> clazzs) {
        mapper = new ObjectMapper();
        this.clazz = clazz;
        this.clazzs = clazzs;
        urlString = Configuration.URL + sufix;
    }

    public Dto save(Dto dto){
        String jsonString;
        HttpURLConnection con;
        try {
            URL url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            jsonString = mapper.writeValueAsString(dto);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonString.getBytes("utf-8");
            os.write(input, 0, input.length);
            os.flush();
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"))) {
                StringBuilder stringBuilder = new StringBuilder();
                String st;
                while ((st = bufferedReader.readLine()) != null){
                    stringBuilder.append(st);
                }
            }
            return dto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveAll(Iterable<Dto> dtos){
        for (Dto dto: dtos)
            save(dto);
    }

    public Dto findById(Long id){
        URL obj;
        try {
            obj = new URL(urlString + "?id=" + id);
            return mapper.readValue(readAsJsonString(obj), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Dto[] findAll(){
        URL obj;
        try {
            obj = new URL(urlString + "/all");
            return mapper.readValue(readAsJsonString(obj), clazzs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readAsJsonString(URL obj) throws IOException {
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null){
            response.append(line);
        }
        reader.close();
        return response.toString();
    }
}
