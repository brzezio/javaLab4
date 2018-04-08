
import java.util.Random;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;

class Lab4
{
	public static void main(String[] args)
	{
		long start,stop;
		Random random=new Random();
		char[] znaki=new char[1000];
		for(int i=0;i<1000;i++)
			znaki[i]=(char)(random.nextInt(94)+33);

		//java.io
		try{
			start=System.currentTimeMillis();
			File fileIo=new File("javaIO");
			fileIo.createNewFile();
			FileWriter fileWriter=new FileWriter(fileIo);
			fileWriter.write(znaki);
			fileWriter.close();
			FileReader fileReader=new FileReader(fileIo);
			char[] pobrane=new char[1000];
			fileReader.read(pobrane);
			fileReader.close();
			stop=System.currentTimeMillis();
			
			System.out.println(pobrane);
			System.out.println("IO czas: "+(stop-start));
			
		}catch(Exception e)
		{
			System.out.println("Error IO");	
		}

		//java.nio
		try{
			start=System.currentTimeMillis();
			FileChannel fileChannel=new FileOutputStream("javaNIO").getChannel();
			byte[] b=new byte[znaki.length];
			for(int i=0;i<znaki.length;i++)
				b[i]=(byte)znaki[i];
			ByteBuffer byteBuffer=ByteBuffer.wrap(b);
			fileChannel.write(byteBuffer);
			fileChannel=new FileInputStream("javaNIO").getChannel();
			byteBuffer.rewind();
			fileChannel.read(byteBuffer);
			char[] pobrane=new char[1000];
			b=byteBuffer.array();
			for(int i=0;i<1000;i++)
				pobrane[i]=(char)b[i];
			stop=System.currentTimeMillis();

			System.out.println(pobrane);
			System.out.println("NIO czas: "+(stop-start));
		}catch(Exception e)
		{
			System.out.println("Error NIO");	
		}
		
		
	}
}
