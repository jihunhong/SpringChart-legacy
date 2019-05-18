package com.cafe24.demo;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/************
 * 
 * 검색어를 입력받고 그 결과에 해당하는 영상들의 url을 반환하는 테스트
 * 
 * Youtube Data API v3
 * 
 ***************/
@RunWith(SpringRunner.class)
@SpringBootTest
public class YoutubeSearchingTest {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    
    private static YouTube youtube;
    
    private final String apikey = "";
    // 유튜브 API 키값

    private String Default_Input_Query = "YouTube Developers Live.";


    @Test
    public void contextLoads() {
    }

    @Test
    public void 유튜브_검색_테스트() {
        
        // https://developers.google.com/youtube/v3/code_samples/java?hl=ko#search_by_keyword

        try {
            
            
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();
            
            String queryTerm = getInputQuery();

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your developer key from the Google Developer Console
             * for non-authenticated requests (found under the API Access tab at this link:
             * code.google.com/apis/). This is good practice and increased your quota.
             */


            String apiKey = apikey;
            search.setKey(apiKey);
            search.setQ(queryTerm);

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults((long) 10);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();

            if (searchResultList != null) {
                prettyPrint(searchResultList.iterator(), queryTerm);
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
            10 + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");
    
        if (!iteratorSearchResults.hasNext()) {
          System.out.println("입력 결과가 존재하지 않습니다.");
        }
    
        while (iteratorSearchResults.hasNext()) {
    
          SearchResult singleVideo = iteratorSearchResults.next();
          ResourceId rId = singleVideo.getId();
    
          // Double checks the kind is video.
          if (rId.getKind().equals("youtube#video")) {
    
            System.out.println(" Video Id :    '" + rId.getVideoId()+"'");
            System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
            System.out.println("\n-------------------------------------------------------------\n");
          }
        }
      }

    private static String getInputQuery() throws IOException {

        String inputQuery = "";
    
        System.out.print("Please enter a search term: ");
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // inputQuery = br.readLine();
    
        // if (inputQuery.length() < 1) {
        //   // If nothing is entered, defaults to "YouTube Developers Live."
        //   inputQuery = "YouTube Developers Live.";
        // }
        
        /*****************************************************************
        
          vscode의 test환경에서 InputstreamReader 사용에 관한 버그가 있어서
          주석화 시키고 임의의 쿼리 넣었음                           
        ********************************************************************/

        inputQuery = "YouTube Developers Live.";
        
        return inputQuery;
      }

    



}
