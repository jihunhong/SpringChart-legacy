# 💿 음원차트 파싱 프로젝트


<!-- @import "[TOC]" {cmd="toc" depthFrom=1 depthTo=6 orderedList=false} -->
<!-- code_chunk_output -->

* [💿 음원차트 파싱 프로젝트](#음원차트-파싱-프로젝트)
	* [📷 ScreenShot](#screenshot)
	* [✒️ 개요](#️-개요)
	* [📁 프로젝트 설계](#프로젝트-설계)
	* [📆 Weekend Commit Preview](#weekend-commit-preview)
	* [💭 아쉬웠던점](#아쉬웠던점)
	* [📚 사용해본것들](#사용해본것들)
	* [🔗 Links](#links)

<!-- /code_chunk_output -->



## 📷 ScreenShot

<center><img src="/screenshot.gif"></center>

## ✒️ 개요
기존의 음원 플랫폼은 지니를 사용하다가

유튜브 레드의 오프라인 저장 기능을 사용해보고 싶어서

3개월 체험권을 받아 이번달 부터 이용해보다가 불편한점이 생겼다.

기존의 음원 플랫폼들에서는 음원 차트가 있어서

신곡이 나오면 들어보고 추가하는게 쉬웠는데

유튜브 레드의 차트는 변동이 심하지 않고 기존곡들 위주로 나오기 때문에

음원 사이트에서 차트를 보고 노래를 추가하는게 너무 번거로웠다.

그래서 지난 프로젝트에서 맛보기로 사용해봤던 JSOUP를 이용해

대표적인 음원사이트들의 차트 정보를 파싱해와서

유튜브 링크를 달아주는 사이트를 개발해보고자 주말동안 프로젝트를 진행해봤다.


## 📁 프로젝트 설계
과거에 진행했었던 프로젝트에서 배운것들과

교육기간동안 배운것들을 합쳐 프로젝트를 진행해봤는데

음원사이트 파싱은 Jsoup를 이용해 차트에 존재하는 필요한 DOM을 가져와서

`ArrayList<음악 정보>`의 형태로 리스트 객체로 만들어서 View에 전달해주는 방식으로 설계했다.

처음에는 익숙한 HashMap으로 `HashMap<제목, 가수이름>`의 형태로 테스트 코드를 만들었었는데

`Thymeleaf`에서 `HashMap` 사용법이 잘 기억 안나기도 했고 ( 주말동안 성과를 내고 싶었다. )

**제목:가수이름 (key:value)** 의 형태로 만들기에는 `HashMap` 구조를 사용하는 목적과 맞지 않는다는 점과

제목, 가수 이름 외에도 나중에 추가될 `Field`들에 대한

**리팩토링이 어려워 질것이라고 생각해서** `ArrayList`로 만들었다.


## 📆 Weekend Commit Preview
<details>
  <summary> <a>19.05.19 첫째 주</a> </summary>

	생각난 아이디어를 해커톤처럼 빠르게 프로젝트로 진행해본게 이번이 두번째다.

	첫번째는 작년 8월 일주일간 진행했었는데

	확실히 실력적으로 많이 성장했던것이 느껴졌었다.

	과거에 해왔었던 프로젝트에서 쓴 코드가 많은 것도 있지만

	교육기간 동안 배운 내용이 중간중간 생각나서 코드를 고치기도 했었고
	( generic을 사용한게 익숙해진 느낌? )

	지난번 프로젝트들보다 설계에 대한 생각이 빠르게 이루어져서 수월하게 했다.

	이번에는 기초 설계를 해보며 테스트 코드를 작성해봤는데

	"이래서 테스트 코드를 쓰는거구나" 하는 생각도 조금은? 느낄 수 있었다 😃

![enter image description here](http://pds21.egloos.com/pds/201902/25/84/c0058984_5c73965f5b9fd.png)

</details>
<details>
  <summary> <a>19.05.26 둘째 주</a> </summary>

	두번째 주는 유튜브 검색 모듈을 서비스단에 따로 만들어 보았다.

	강의내용에서 강조했던 3-Layer의 분리를 진행해 봤는데

	View단에서 요청을 통해 유튜브 모듈을 이용할지 아니면

	컨트롤러단에서 부터 파싱할때 해당 정보를 한번에 검색해서 넣어줄지가 고민이다.

	금요일에 Jenkin를 통한 CI를 배웠는데 tomcat을 실습 예제로 사용해서

	Nginx 예제를 보며 따라해볼 생각이었으나 시간이 너무 없어 이번주에는 진행하질 못했다.

	ul list 태그로 테이블을 만드는게 처음이어서 그런가 정렬에 생각보다 시간이 많이걸렸다.

</details>



## 💭 아쉬웠던점
<details>
  <summary> <a>19.05.19 첫째 주</a> </summary>
	처음 프로젝트를 설계했을때 생각했던 기능을 못넣은게 많아서 아쉬운점도 있다.

	대표적으로는 각각의 차트 항목의 정보를 받아 해당하는 유튜브 영상을

	링크해서 추가할 수 있게 하려고 했었는데

	금요일 퇴근후 부터 현재까지 너무 달려서 지쳐버렸다. 💦

	네이버 클라우드 플랫폼 (NCP)를 뉴스 기사에서 보고

	도메인 구입과 NCP에 업로드까지 하려고 했는데 다음주나 시간되면 진행해야 겠다.

	또한 일정이 촉박하다는 생각이 자꾸 들어서

	View단의 리소스를 구하는데 시간을 많이 들이지 못해

	프로젝트 View단의 퀄리티가 만족할만큼 나오지 않아서 조금 아쉬웠다.
</details>

<details>
  <summary> <a>19.05.26 둘째 주</a> </summary>

	이번주는 본격적인 스프링 진도에 나가면서 `TIL` 포스팅도 조금씩 미뤄지고

	프로젝트도 만족할 만큼 진도를 못나갔다.

	호기롭게 시작한 토이 프로젝트였었는데 배운 내용을 토대로 설계 해보자니

	생각이 많아져서 시간이 오래걸린 것 같다.

	다음주 부터는 이슈탭을 적극적으로 이용해 봐야겠다.

</details>

## 📚 사용해본것들
![stack](https://i.imgur.com/ui5Ys82.png)
- Spring Boot
- Thymeleaf
- YouTube Data API
- JSOUP
- Mp3agic
- MDB


## 🔗 Links

- [YouTube Data API v3](https://developers.google.com/youtube/v3/getting-started?hl=ko)
- [Jsoup.org](https://jsoup.org/)
- [Mp3agic](https://github.com/mpatric/mp3agic)
- [MDB](https://mdbootstrap.com/freebies/)
- [블로그](https://redgee.tistory.com/)
