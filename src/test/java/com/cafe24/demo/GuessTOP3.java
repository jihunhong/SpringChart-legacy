package com.cafe24.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.demo.DAO.VideoDAO;
import com.cafe24.demo.Utils.JsoupUtils;
import com.cafe24.demo.VO.Music;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GuessTOP3 extends JsoupUtils {

    @Autowired
    private VideoDAO videoDAO;

    @Test
    public void 저장여부_판별_코드() {
        // TOP3 에 해당하는 항목들은 DB에 저장됩니다.
        // Request시 크롤링되는 음원정보가 DB에 있는지를 판별하는 테스트 코드입니다.
        List<Music> list = getMelonChart();

        for( Music music : list ){
			System.out.println(music.toString());
        }

    }

    @Override
    public List<Music> getMelonChart() {
        List<Music> list = new ArrayList<Music>();

		try {

			String url = "https://www.melon.com/chart/";

			Document document = Jsoup.connect(url).get();

			Elements music_titles = document.getElementsByClass("ellipsis rank01");
			// 노래 제목

			Elements artist_names = document.getElementsByClass("ellipsis rank02");
			// 가수 이름

			Elements album_names = document.getElementsByClass("ellipsis rank03");
			// 앨범 이름
			
			Elements album_imgs = document.getElementsByClass("image_typeAll");
			// 앨범 이미지

			for (int i = 0; i < 3; i++) {

				String title = music_titles.get(i).text();
				String artist = artist_names.get(i).getElementsByTag("span").text();
				String album = album_names.get(i).text();
				
				String img_url = album_imgs.get(i).select("img").attr("src").toString();

				Music music = new Music();
				music.setRank(i + 1);
				music.setTitle(title);
				music.setArtist(artist);
				music.setAlbum(album);
				music.setUrl(img_url);

				list.add(music);
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
    }


    


}