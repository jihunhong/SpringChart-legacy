package com.cafe24.demo.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.demo.VO.Music;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupUtils {

	public List<Music> getBugsChart() {

		List<Music> list = new ArrayList<Music>();

		try {

			String url = "https://music.bugs.co.kr/chart";

			Document document = Jsoup.connect(url).get();

			Elements music_titles = document.getElementsByClass("title");
			// 노래 제목

			Elements artist_names = document.getElementsByClass("artist");
			// 가수 이름

			Elements album_imgs = document.getElementsByClass("thumbnail");
			// 앨범 이미지

			for (int i = 0; i < 50; i++) {

				String title = music_titles.get(i+3).text();
				String artist = artist_names.get(i + 1).select("p").select("a").get(0).text();

				String img_url = album_imgs.get(i).select("img").attr("src").toString();

				Music music = new Music();
				music.setRank(i + 1);
				music.setTitle(title);
				music.setArtist(artist);
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

			for (int i = 0; i < 50; i++) {

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

	public List<Music> getGeineChart() {

		List<Music> list = new ArrayList<Music>();

		try {

			String url = "https://genie.co.kr/chart/top200";

			Document document = Jsoup.connect(url).get();

			Elements music_titles = document.getElementsByClass("title ellipsis");
			// 노래 제목

			Elements artist_names = document.getElementsByClass("artist ellipsis");
			// 가수 이름

			Elements album_names = document.getElementsByClass("albumtitle ellipsis");
			// 앨범 이름

			Elements album_imgs = document.getElementsByClass("cover");
			// 앨범 이미지

			for (int i = 0; i < 50; i++) {

				String title = music_titles.get(i).text();
				String artist = artist_names.get(i + 5).text();
				String album = album_names.get(i).text();

				String img_url = album_imgs.get(i + 5).select("img").attr("src").toString();
				
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

	// public String SaveImg(String url){


	// 	return "";
	// }
}