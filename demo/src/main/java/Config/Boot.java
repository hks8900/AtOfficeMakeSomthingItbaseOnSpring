package Config;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.example.demo.Helper.FileHepler;
import com.example.demo.Helper.HttpRequester;

import kong.unirest.UnirestException;


public class Boot implements ApplicationRunner {
	@Autowired
	CrawlerService CService;

	@Autowired
	HttpRequester HTD;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		for (String check : StaticResource.InerLocation) {
			FileHepler.folderExist(check);
		}
		crawl(StaticResource.homePage);// 크롤링할 주소 입력
	}

//임시 포지션 
	private void crawl(String url) throws UnirestException {
		Document doc = HttpRequester.makeDoc(url);
		CService.CrawlInfo(doc);

	}

}
