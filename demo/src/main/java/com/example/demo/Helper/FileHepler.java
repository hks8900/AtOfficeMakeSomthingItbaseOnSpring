package com.example.demo.Helper;



import java.io.File;
import java.nio.file.Files;

import javax.activation.MimetypesFileTypeMap;

import Config.StaticResource;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

	public class FileHepler {

		public static boolean folderExist(String path) {
			File folder = new File(path);
			if (folder.exists()) {
				return true;
			} else {
				System.out.println("making folder");
				return folder.mkdir();
			}
		}

		public static File GetFile(String src) { // src에 파일 이름이 있는 경우 
//			String fileName = "/" + src.substring(src.lastIndexOf("/"));
			String fileName = StaticResource.extractFileNameFromSrc(src);

			File file = new File(StaticResource.assetCover(StaticResource.downLocation(src) + fileName));

			File result = null;

			if (file.exists()) { // 기존 파일 리턴
				result = file;
			} else {
				String DownSrc = StaticResource.addressChecker(src);

				HttpResponse<File> fileDown = Unirest.get(StaticResource.homePage + DownSrc)
						.asFile(StaticResource.assetCover(StaticResource.downLocation(src) + fileName));
				if (fileDown.isSuccess()) {
					result = fileDown.getBody();
				} else {// 리얼 실패
				}
			}
			return result;
		}

		public static HttpResponse<File> DownBannerFile(String src) {
			String fileName = StaticResource.extractFileNameFromSrc(src);
			String path = StaticResource.InerLocation[2];

			String DownSrc = StaticResource.addressChecker(src);

			HttpResponse<File> fileDown = Unirest.get(StaticResource.homePage + DownSrc)
					.asFile(StaticResource.assetCover(path + fileName));

			return fileDown;
		}

		public static HttpResponse<File> DownBannerFileN(String src, String fileName) {

			String path = StaticResource.InerLocation[2];

			String DownSrc = StaticResource.addressChecker(src);

			HttpResponse<File> fileDown = Unirest.get(StaticResource.homePage + DownSrc)
					.asFile(StaticResource.assetCover(path + fileName));
			return fileDown;

		}

		public static String infoFile(HttpResponse<File> fileDown) {

			String mimeType = "application/octet-stream";
			if (fileDown.isSuccess()) {

				File dl = fileDown.getBody();
				System.err.println(dl);

				try {
					mimeType = Files.probeContentType(dl.toPath());
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("banner mimeType fail");
				}
				System.out.println("[DOWN OK] ");
			} else {
				System.out.println("fileDown part");
				mimeType = fileDown.getHeaders().getFirst("Content-Type");

			}

			return mimeType;

		}

		public static String infFile(File fileDown) {

			MimetypesFileTypeMap mimeTypesMap = new MimetypesFileTypeMap();

			String mimeType = mimeTypesMap.getContentType(fileDown.getAbsolutePath());

			return mimeType;
		}

	}


