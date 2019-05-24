package com.cafe24.demo.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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

import org.springframework.stereotype.Service;

@Service
public class YoutubeService {

    private final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    private static YouTube youtube;

    private final String apikey = "";

    public List<SearchResult> SearchOnYoutube(String searchQuery) {

        List<SearchResult> searchResultList = null;

        try {

            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your developer key from the Google Developer Console
             * for non-authenticated requests (found under the API Access tab at this link:
             * code.google.com/apis/). This is good practice and increased your quota.
             */

            String apiKey = apikey;
            search.setKey(apiKey);

            search.setQ(searchQuery);
            // 검색어 설정

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults((long) 10);
            SearchListResponse searchResponse = search.execute();

            searchResultList = searchResponse.getItems();

        } catch (Throwable t) {
            t.printStackTrace();
        }

        return searchResultList;
    }

}
