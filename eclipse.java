package file;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
class Test {
	public static void playSound()
	{
		File file=new File("C:\\Users\\수만\\workspace\\sooman\\src\\file\\Kelly Clarkson - Because Of You.wav");
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
	public static void main(String[]args){
		playSound();
		System.out.println("\n");
		System.out.println("\\n");
		
	}
}
