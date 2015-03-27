
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ConnectedClient 
{
	private Socket theSocket;
	private PrintWriter output;
	private Scanner input;

	public ConnectedClient(Socket theSocket) 
	{
		try
		{
			this.theSocket = theSocket;
			this.output = new PrintWriter(this.theSocket.getOutputStream(), true);
			this.input = new Scanner(this.theSocket.getInputStream());
			System.out.println("Connected!");	 
		    File myFile = new File("./myFiles");
		      byte[] mybytearray = new byte[(int) myFile.length()];
		      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
		      bis.read(mybytearray, 0, mybytearray.length);
		      OutputStream os = theSocket.getOutputStream();
		      os.write(mybytearray, 0, mybytearray.length);
		      os.flush();
		      theSocket.close();
		}
		catch(Exception e)
		{
			//yea right
		}
	}
	
	public void sendMessage(String msg)
	{
		this.output.println(msg);
	}

	public String getMessage()
	{
		return this.input.nextLine();
	}

}
