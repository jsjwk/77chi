package com.bb.neighbor.renren.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.param.impl.SessionKey;
import com.renren.api.client.services.RenrenApiException;

@Path("/uploadphoto")
public class UploadPhotoController {

	@Get("/show")
	public String showUploadPhoto() {
		return "uploadphoto";
	}

	@Post("/upload")
	public void uploadPhoto(Invocation inv) {
		HttpServletRequest request = inv.getRequest();
		String flag = (String) request.getParameter("flag");
		String sessionKey = (String) request.getSession().getAttribute("session_key");
		if (flag != null && sessionKey != null) {
			RenrenApiClient client = RenrenApiClient.getInstance();
			if (flag.equals("local")) {
				// 上传本地图片
				String filename = (String) request.getParameter("filename");
				try {
					filename = new String(filename.getBytes("ISO8859-1"), "UTF-8");
					client.getPhotoService().uploadLocalImg(0, filename, "测试上传本地图片" + new Date(), new SessionKey(sessionKey));
				} catch (RenrenApiException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

			} else if (flag.equals("url")) {
				// 上传网络图片
				String fileurl = (String) request.getParameter("fileurl");
				try {
					client.getPhotoService().uploadURLImg(0, fileurl, "测试上传网络图片" + new Date(), new SessionKey(sessionKey));
				} catch (RenrenApiException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
