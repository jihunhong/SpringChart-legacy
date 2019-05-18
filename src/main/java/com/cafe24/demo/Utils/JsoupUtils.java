package com.cafe24.demo.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.demo.VO.Music;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupUtils {

    public List<Music> getBugsChart(){

		List<Music> list = new ArrayList<Music>();

		try{

			String url = "https://music.bugs.co.kr/chart";

			Document document = Jsoup.connect(url).get();

			Elements music_titles = document.getElementsByClass("title");
			// 노래 제목
			

			Elements artist_names = document.getElementsByClass("artist");
			// 가수 이름
			
			for(int i=0; i < 50; i++){

				String title  = music_titles.get(i+3).text();
                String artist = artist_names.get(i+1).text();
                
                Music music = new Music();
                music.setRank(i+1);
                music.setTitle(title);
                music.setArtist(artist);

                list.add(music);
			}

		}catch(NullPointerException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
        }
        
        return list;
	}


    public List<Music> getMelonChart(){
        
		List<Music> list = new ArrayList<Music>();

		try{

			String url = "https://www.melon.com/chart/";

			Document document = Jsoup.connect(url).get();

			Elements music_titles = document.getElementsByClass("ellipsis rank01");
			// 노래 제목
			

			Elements artist_names = document.getElementsByClass("ellipsis rank02");
			// 가수 이름
			
			for(int i=0; i < 50; i++){

				String title  = music_titles.get(i).text();
                String artist = artist_names.get(i).getElementsByTag("span").text();
                
                Music music = new Music();
                music.setRank(i+1);
                music.setTitle(title);
                music.setArtist(artist);

                list.add(music);
			}

		}catch(NullPointerException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
        }
        
        return list;
    }

    public List<Music> getGeineChart(){
        
		List<Music> list = new ArrayList<Music>();

		try{

			String url = "https://genie.co.kr/chart/top200";

			Document document = Jsoup.connect(url).get();

			Elements music_titles = document.getElementsByClass("title ellipsis");
			// 노래 제목
			

			Elements artist_names = document.getElementsByClass("artist");
			// 가수 이름
			
			for(int i=0; i < 50; i++){

				String title  = music_titles.get(i).text();
                String artist = artist_names.get(i+5).text();
                
                Music music = new Music();
                music.setRank(i+1);
                music.setTitle(title);
                music.setArtist(artist);

                list.add(music);
			}

		}catch(NullPointerException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
        }
        
        return list;
    }
}