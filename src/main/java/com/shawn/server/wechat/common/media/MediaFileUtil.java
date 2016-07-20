package com.shawn.server.wechat.common.media;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 上传和下载多媒体文件（目前仅用于下载临时文件）
 * 
 * @author shawn
 *
 */
public class MediaFileUtil {

	private static final String DOWNLOAD_API_URL = "https://api.weixin.qq.com/cgi-bin/media/get";
	private static final String PARAM_ACCESS_TOKEN = "access_token";
	private static final String PARAM_MEDIA_ID = "media_id";

	private File file;
	private String mediaId;
	private String accessToken;

	public MediaFileUtil() {
	}

	public MediaFileUtil(String mediaId, String accessToken) {
		this.mediaId = mediaId;
		this.accessToken = accessToken;
	}

	public MediaFileUtil(File file, String mediaId, String accessToken) {
		this.file = file;
		this.mediaId = mediaId;
		this.accessToken = accessToken;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 文件下载url生成
	 * 
	 * @return
	 */
	private String downloadUrl() {
		String url = DOWNLOAD_API_URL + "?" + PARAM_ACCESS_TOKEN + "=" + accessToken + "&" + PARAM_MEDIA_ID + "="
				+ mediaId;
		return url;
	}

	/**
	 * 通过url获得字节数组
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private byte[] getDataByUrl(String path) throws IOException {
		URL url = null;
		byte[] data = null;
		url = new URL(path);
		data = readInputStream(url.openStream());
		return data;
	}

	/**
	 * 从输入流中获取字节数组
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private byte[] readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * 下载文件
	 * 
	 * @param mediaId
	 * @return
	 * @throws IOException
	 */
	public byte[] download(String mediaId) throws IOException {
		this.mediaId = mediaId;
		return download();
	}

	/**
	 * 下载文件
	 * 
	 * @return
	 * @throws IOException
	 */
	public byte[] download() throws IOException {
		String urlPath = downloadUrl();
		return getDataByUrl(urlPath);
	}

}
