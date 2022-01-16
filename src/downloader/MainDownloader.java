package downloader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainDownloader
{
	public static String vidLink;
	public static String vidID;
	public static String thumbMaxRes;
	public static String thumbHq;
	public static int thumbRes;

	public static void main(String[] args)
	{
		System.out.println();
		cta();
		System.out.println("\nThumbnail image will be saved in D drive.");
		String vidURL = getVidUrl();

			try
			{
				String[] arrOfStr = vidURL.split("&");
				String urlWithoutTimeStamp;
				urlWithoutTimeStamp = vidURL.replace("&"+arrOfStr[1], "");
				vidID = urlWithoutTimeStamp.replace("https://www.youtube.com/watch?v=", "");
				System.out.println("Thumbnail location: D:\\thumbnail_"+vidID+".jpg");
			}
			catch (Exception e)
			{
				try
				{
					String[] arrOfStr = vidURL.split("=");
				//	vidID = vidURL.replace("https://www.youtube.com/watch?v=", "");
					vidID = arrOfStr[1];
					System.out.println("Thumbnail location: D:\\thumbnail_"+vidID+".jpg");
				} catch (Exception e1)
				{
					vidID = vidURL.replace("https://youtu.be/", "");
					System.out.println("Thumbnail location: D:\\thumbnail_"+vidID+".jpg");
				}
			}
		

		thumbMaxRes = "http://i.ytimg.com/vi/"+vidID+"/maxresdefault.jpg";
		thumbHq = "http://i.ytimg.com/vi/"+vidID+"/hqdefault.jpg";

		File out = new File("D:\\thumbnail_"+vidID+".jpg");

		switch(getThumRes())
		{
		case 1: new Thread(new Download(thumbMaxRes, out)).start();
		System.out.println("Downloading HD Thumbnail...");
		break;
		case 2: new Thread(new Download(thumbHq, out)).start();
		System.out.println("Downloading SD Thumbnail...");
		break;
		default: new Thread(new Download(thumbHq, out)).start();
		System.out.println("Downloading SD Thumbnail...");
		}

	}

	@SuppressWarnings("resource")
	public static String getVidUrl()
	{
		System.out.print("Enter video url: ");
		Scanner sc = new Scanner(System.in);
		vidLink = sc.nextLine();
		return vidLink;
	}

	@SuppressWarnings("resource")
	public static int getThumRes()
	{
		System.out.println("\nIf HD thumbnail throws error, try with SD thumbnail.");
		System.out.println("Select thumbnail resolution:");
		System.out.println("1. HD Thumbnail");
		System.out.println("2. SD Thumbnail");
		System.out.print("Enter thumbnail resolution (1/2): ");
		Scanner sc = new Scanner(System.in);
		thumbRes = sc.nextInt();
		return thumbRes;
	}

	public static void cta()
	{
		System.out.println("Follow me on Instagram: https://instagram.com/akhtarreviews");
		System.out.println("Subscribe to my YouTube channel: https://www.youtube.com/akhtarreviews");
	}
}
