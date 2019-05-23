package netty.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainOioServer
{
    
    public void serve(int port) throws Exception 
    {
        // builds the server to the application port
        final ServerSocket socket = new ServerSocket(port);
        try
        {
            for(;;)
            {
                // accept a connection
                final Socket clientSocket = socket.accept();
                System.out.printf("Accepted connection from " + clientSocket);
                // create new thread to handle the connection
                new Thread(new Runnable() {

                    @Override
                    public void run()
                    {
                        OutputStream out;
                        try
                        {
                            out = clientSocket.getOutputStream();
                            // write the message to conneted client
                            out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                            out.flush();
                            // close the client
                            clientSocket.close();
                        }
                        catch(IOException e)
                        {
                            e.printStackTrace();
                        }
                        finally
                        {
                            try
                            {
                                clientSocket.close();
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                    // start thread
                }).start();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
