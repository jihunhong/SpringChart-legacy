package com.cafe24.demo.Service;

import static com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.load;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.java6.auth.oauth2.FileCredentialStore;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Data;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeScopes;
import com.google.api.services.youtube.YouTube.PlaylistItems;
import com.google.api.services.youtube.YouTube.Playlists;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistListResponse;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class YoutubeService {

    private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.dir"), "./credential/client_secret.json");

    private static FileDataStoreFactory DATA_STORE_FACTORY; 

    private GoogleAuthorizationCodeFlow flow;

    private static YouTube youtube;

    @Value("${apikey}")
    private String apikey = "";

    public ArrayList<Map<String, String>> SearchOnYoutube(String searchQuery) {

        List<SearchResult> searchResultList = null;
        SearchListResponse searchResponse = null;

        try {

            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("ChartCrawler").build();

            YouTube.Search.List search = youtube.search().list("id,snippet");

            /*
             * It is important to set your developer key from the Google Developer Console
             * for non-authenticated requests (found under the API Access tab at this link:
             * c). This is good practice and increased your quota.
             */

            String apiKey = apikey;
            search.setKey(apiKey);

            search.setQ(searchQuery);
            // 검색어 설정

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/high/url)");
            search.setMaxResults((long) 3);
            searchResponse = search.execute();

            searchResultList = searchResponse.getItems();
            System.out.println(searchResponse.toString());

        } catch (Throwable t) {
            t.printStackTrace();
        }
        ResourceId rId;
        Thumbnail thumbnail;
        ArrayList<Map<String, String>> output = new ArrayList<>();

        for (SearchResult result : searchResponse.getItems()) {
            SearchResultSnippet snippet = result.getSnippet();
            rId = result.getId();

            thumbnail = result.getSnippet().getThumbnails().getHigh();

            Map<String, String> element = new HashMap<String, String>();
            element.put("title", snippet.getTitle());
            element.put("url", rId.getVideoId());
            element.put("thumbnail", thumbnail.getUrl());
            output.add(element);
        }
        return output;
    }

    public void GetPlayListMine() {

        List<Playlist> searchResultList = null;
        PlaylistListResponse playlistresponse = null;

        try {
            // Authorization.
            Credential credential = authorize();
      
            // YouTube object used to make all API requests.
            
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName("ChartCrawler").build();

            YouTube.Playlists.List list = youtube.playlists().list("snippet");

            String apiKey = apikey;
            list.setKey(apiKey);

            list.setMine(true);
            

            playlistresponse = list.execute();

            searchResultList = playlistresponse.getItems();
            System.out.println(playlistresponse.toPrettyString());

      
          } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
            e.printStackTrace();
          } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
          } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    }

    private static Credential authorize() throws Exception {
        List<String> scopes = Arrays.asList(YouTubeScopes.YOUTUBE);

        InputStream in = null;
        
        try{
            in = YoutubeService.class.getResourceAsStream("/client_secret.json");
            System.out.println(in);
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
            new InputStreamReader(in));
          
            if (clientSecrets.getDetails().getClientId().startsWith("Enter")
              || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            System.out.println(
                "Enter Client ID and Secret from https://code.google.com/apis/console/?api=youtube"
                + "into youtube-cmdline-playlistupdates-sample/src/main/resources/client_secrets.json");
            System.exit(1);
          }
            // Set up file credential store.
            FileCredentialStore credentialStore =
                new FileCredentialStore(
                  DATA_STORE_DIR,
                            JSON_FACTORY);
            GoogleAuthorizationCodeFlow flow = 
                new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, 
                                                        JSON_FACTORY,
                                                        clientSecrets,
                                                        scopes)
                                                        .setDataStoreFactory(new FileDataStoreFactory(DATA_STORE_DIR))
                                                        .build();

            Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
            System.out.println("Credential Saved to " + DATA_STORE_DIR.getAbsolutePath());

            return credential;
            
        }catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : " + e.getDetails().getMessage());
            e.printStackTrace();
          } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            e.printStackTrace();
          } catch (Throwable t) {
            System.err.println("Throwable: " + t.getMessage());
            t.printStackTrace();
          }finally{
            
        }
        return null;
    }

    
    
}
