package Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class StaticResource implements WebMvcConfigurer {

	public static String[] InerLocation;
	
	public static String homePage;

	@Value("${external.resource.href}") // 외부 경로 입력 방식
	public  void homePage(String homePage) { 
		StaticResource.homePage = homePage;
	}

	@Value("${external.resource.path}")
	private String staticResourceLocation;

	@Value("${external.resource.save.path.mainFolder}")
	public void inerLocation(String[] InerLocation) {
		StaticResource.InerLocation = InerLocation;

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("asset/**").addResourceLocations(staticResourceLocation);
	}

	public static String assetCover(String location) {
		String Cover = location.replace(StaticResource.InerLocation[0], "\\\\asset\\\\");
		return Cover.replaceAll("\\\\\\\\", "/");
	}

	public static String addressChecker(String src) { // 소스 주소 필터 
		String DownSrc;
		if (src.contains(homePage)) {
//			DownSrc = src.substring(src.lastIndexOf(homePage)); 전체 주소가 나옴

			DownSrc = src.split(StaticResource.homePage)[1];
		} else {
			DownSrc = src;
		}
		return DownSrc;

	}
	
	public static String extractFileNameFromSrc(String src) { // 소스에서 파일 이름 추출

		return src.substring(src.lastIndexOf("/") + 1);
	}

	public static String downLocation(String href) { // 주소에 따른 저장위치
		String saveLocation = "";
		if (href.contains("/board/view.chuncheon")) {
			saveLocation = StaticResource.InerLocation[2];
		} else if (href.contains("boardId=BBS_0000505")) {
			saveLocation = StaticResource.InerLocation[3];

		} else if (href.contains("boardId=BBS_0000257")) {
			saveLocation = StaticResource.InerLocation[1];
		} else {
			System.out.println(href + " ////" + "ExchageLocation");
			saveLocation = StaticResource.InerLocation[0];
		}
		return saveLocation;
	}


}
