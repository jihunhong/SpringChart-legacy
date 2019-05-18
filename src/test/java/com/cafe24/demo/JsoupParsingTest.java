package com.cafe24.demo;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.Jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/************

url을 입력받고
해당 url의 HTML을 파싱하는 라이브러리인 
JSOUP 테스트 클래스 입니다.

***************/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JsoupParsingTest {

	@Test
	public void contextLoads() {
    }
	
	@Test
	public void 블로그_메타태그_파싱_테스트(){
		Map<String, String> map = new HashMap<>();

		try{
			String url = "https://jihunhong.github.io";

			Document document = Jsoup.connect(url).get();
			
			Elements elments = document.select("meta");
			// HTML문서에서 meta태그 모두를 배열의 형태를 띤 Elements 담는다
            
			
			for(Element elment : elments){
                map.put(elment.attr("property"), elment.attr("content"));
				// System.out.println(elment.attr("property") + "  :  " + elment.attr("content"));
			// 	:  
			// 	:  width=device-width, initial-scale=1
			// 	:  Hugo 0.55.3 with theme Tranquilpeak 0.4.3-BETA
			// 	:  JIHUN HONG
			// 	:  
			// 	:  harry's blog
			//   og:description  :  harry's blog
			//   og:type  :  blog
			//   og:title  :  harry's blog
			//   og:url  :  /
			//   og:site_name  :  harry's blog
			// 	:  summary
			// 	:  harry's blog
			// 	:  harry's blog
			//   og:image  :  //www.gravatar.com/avatar/1195e5a46b27d50fe7234f2e57a8a24c?s=640                
            }
		}catch(NullPointerException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

		assertNotNull(map);

	}

	@Test
	public void 멜론_차트_파싱_테스트_1to100(){

		Map<String, String> music_info = new HashMap<>();
		// 파싱한 데이터를
		// Map에 <노래제목, 가수이름> 형태로 담는다

		try{
			String url = "https://www.melon.com/chart/";

			Document document = Jsoup.connect(url).get();
			
			Elements music_titles = document.getElementsByClass("ellipsis rank01");
			// 노래 제목

			Elements artist_names = document.getElementsByClass("ellipsis rank02");
			// 가수 이름
			
			for(int i=0; i < 100; i++){

				String title  = music_titles.get(i).text();
				String artist = artist_names.get(i).getElementsByTag("span").text();

                music_info.put( title , artist );
				// System.out.println( title + " : " + artist );
				// 작은 것들을 위한 시 (Boy With Luv) feat. Halsey	:	방탄소년단
				// 사랑에 연습이 있었다면 (Prod. 2soo)	:	임재현
				// AH YEAH (아예)	:	WINNER
				// 주저하는 연인들을 위해	:	잔나비
				// Goodbye	:	박효신
				// 너에게 못했던 내 마지막 말은	:	다비치           
			}
			
		}catch(NullPointerException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}

		assertNotNull(music_info);
	}

	@Test
	public void 지니뮤직_차트_파싱_테스트_1to50(){
		Map<String, String> music_info = new HashMap<>();

		try{

			String url = "https://genie.co.kr/chart/top200";

			Document document = Jsoup.connect(url).get();

			Elements music_titles = document.getElementsByClass("title ellipsis");
			// 노래 제목

			Elements artist_names = document.getElementsByClass("artist ellipsis");
			// 가수 이름
			
			for(int i=0; i < 50; i++){

				String title  = music_titles.get(i).text();
				String artist = artist_names.get(i+5).text();
				// 1위부터 5위까지 표시해주는 프리뷰가 있어서 5부터 시작해야함

                music_info.put( title , artist );
				System.out.println( title + " : " + artist );       
			}

		}catch(NullPointerException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		assertNotNull(music_info);
	}

	@Test
	public void 벅스뮤직_차트_파싱_테스트_1_to100(){
		Map<String, String> music_info = new HashMap<>();

		try{

			String url = "https://music.bugs.co.kr/chart";

			Document document = Jsoup.connect(url).get();

			Elements music_titles = document.getElementsByClass("title");
			// 노래 제목
			

			Elements artist_names = document.getElementsByClass("artist");
			// 가수 이름
			
			for(int i=0; i < 100; i++){

				String title  = music_titles.get(i+3).text();
				String artist = artist_names.get(i+1).text();

                music_info.put( title , artist );
				System.out.println( title + " : " + artist );    
				// 너에게 못했던 내 마지막 말은 : 다비치
				// AH YEAH (아예) : WINNER
				// 비가 오잖아 : 소유 (SOYOU) 소유 (SOYOU)
				// 주저하는 연인들을 위해 : 잔나비
				// 작은 것들을 위한 시 (Boy With Luv) (feat. Halsey) : 방탄소년단 방탄소년단
				// FANCY : TWICE (트와이스)
				// 다섯 번째 계절 (SSFWL) : 오마이걸(OH MY GIRL)
				// 2002 : Anne-Marie(앤 마리)
				// 뜨거운 여름밤은 가고 남은 건 볼품없지만 : 잔나비
				// bad guy : Billie Eilish(빌리 아일리시)
				// 사랑에 연습이 있었다면 (Prod. 2soo) : 임재현   
			}

		}catch(NullPointerException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		assertNotNull(music_info);
	}



    



}
