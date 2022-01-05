package downloader;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Download extends MainDownloader implements Runnable
{
	String link;
	File out;
	
	public Download(String link, File out)
	{
		this.link = link;
		this.out = out;
	}

	@Override
	public void run()
	{
		try
		{
			URL url = new URL(link);
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			double fileSize = (double)http.getContentLengthLong();
			BufferedInputStream in = new BufferedInputStream(http.getInputStream());
			FileOutputStream fos = new FileOutputStream(this.out);
			BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
			byte[] buffer = new byte[1024];
			double downloaded = 0.00;
			int read = 0;
			double percentDownloaded = 0.00;
			
			while((read = in.read(buffer, 0, 1024)) >= 0)
			{
				bout.write(buffer, 0, read);
				downloaded += read;
				percentDownloaded = (downloaded*100)/fileSize;
				String percent = String.format("%.2f", percentDownloaded);
				try
				{
					clearConsole();
				} catch (Exception e)
				{
					e.printStackTrace();
				}
				System.out.println("Thumbnail image will be saved in D drive.");
				System.out.println("Thumbnail Downloaded: " + percent+"%");
			}
			openDownloadedImage();
			bout.close();
			in.close();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void openDownloadedImage()
	{
		try  
		{  

			File file = new File("D:\\thumbnail_"+vidID+".jpg");   
			if(!Desktop.isDesktopSupported()) 
			{  
				System.out.println("not supported");  
				return;  
			}  
			Desktop desktop = Desktop.getDesktop();  
			if(file.exists())          
				desktop.open(file);              
		}  
		catch(Exception e)  
		{  
			e.printStackTrace();  
		}  
	}
	
	public static void clearConsole() throws Exception
	{
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	}
	
}
