package file;

import java.awt.FlowLayout;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
class Test {
	public static void playSound(String path)
	{
		File file=new File(path);
		AudioInputStream audioInputStream=null;
		SourceDataLine auline=null;
		try{
			audioInputStream=AudioSystem.getAudioInputStream(file);
		}
		catch(UnsupportedAudioFileException e1){
			e1.printStackTrace();
			return;
		}
		catch(IOException e1){
			e1.printStackTrace();
			return;
		}
		AudioFormat format=audioInputStream.getFormat();
		DataLine.Info info=new DataLine.Info(SourceDataLine.class, format);
		try{
			auline=(SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		}
		catch(LineUnavailableException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
			return;
		}
		auline.start();
		
		int nBytesRead=0;
		final int EXTERNAL_BUFFER_SIZE=524288;
		byte[] abData=new byte[EXTERNAL_BUFFER_SIZE];
		
		try{
			while(nBytesRead!=-1){
				nBytesRead=audioInputStream.read(abData,0,abData.length);
				if(nBytesRead>=0)
					auline.write(abData, 0, nBytesRead);
			}
		}
		catch(IOException e){
			e.printStackTrace();
			return;
		}
		finally{
			auline.drain();
			auline.close();
		}
	}
	public static Action music(){
			playSound("C:\\Users\\수만\\workspace\\sooman\\src\\file\\Adele - Rolling In The Deep.wav");
		return null;
	}
	public static void main(String[]args){
		System.out.printf("1.Because of you-Kelly clarkson\n2.Rolling in the deep-Adele\n3.Safe&Sound-Taylor Swift\n4.Counting Stars-One Republic\n5.Party in the U.S.A-Miley Cyrus\n선택 : ");
		Scanner keyboard=new Scanner(System.in);
		int num=keyboard.nextInt();
		switch(num){
		case 1 : 
			playSound("C:\\Users\\수만\\workspace\\sooman\\src\\file\\Kelly Clarkson - Because Of You.wav");
			break;
		case 2 :
			playSound("C:\\Users\\수만\\workspace\\sooman\\src\\file\\Adele - Rolling In The Deep.wav");
			break;
		case 3 :
			playSound("C:\\Users\\수만\\workspace\\sooman\\src\\file\\Taylor Swift ft. The Civil Wars - Safe &amp; Sound (The Hunger Games).wav");
			break;
		case 4 :
			playSound("C:\\Users\\수만\\workspace\\sooman\\src\\file\\One Republic - Counting Stars - Lyrics Video (Native Album) [HD][HQ].wav");
			break;
		case 5 :
			playSound("C:\\Users\\수만\\workspace\\sooman\\src\\file\\Miley Cyrus - Party In The U.S.A. - Official Music Video (HD) (1).wav");
			break;
		}
	}
}

