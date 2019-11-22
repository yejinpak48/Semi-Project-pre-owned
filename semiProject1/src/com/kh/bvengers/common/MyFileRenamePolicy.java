package com.kh.bvengers.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{

	@Override
	public File rename(File oldFile) {
		long currentTime = System.currentTimeMillis();

		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmmss");
		int randomNumber = 0;

		for(;;) {
		randomNumber = (int)(Math.random() * 1000000);

			if(randomNumber >100000) {
				break;
			}
		}

		//파일의 파일명과 확장자명 분리
		String name = oldFile.getName();
		String body = null;
		String ext = null;

		int dot = name.lastIndexOf(".");

		if(dot != -1) {
			body = name.substring(0, dot);
			ext = name.substring(dot);
		}else {
			body = name;
			ext = "";
		}

		String fileName = ft.format(new Date(currentTime)) + randomNumber + ext;

		File newFile = new File(oldFile.getParent(), fileName);

		return newFile;
	}

}





















