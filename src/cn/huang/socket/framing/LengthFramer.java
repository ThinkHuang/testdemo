package socket.framing;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class LengthFramer implements Framer
{
    public static final int MAXMESSAGELENGTH = 65535;
    public static final int BYTEMASK = 0xff;
    public static final int BYTESHIFT = 8;
    
    private DataInputStream in;
    
    public LengthFramer(InputStream in) {
        this.in = new DataInputStream(in);
    }
    
    
    @Override
    public void frameMsg(byte[] message, OutputStream out) throws IOException
    {
        //消息长度不能超过65535
        if(message.length > MAXMESSAGELENGTH) {
            throw new IOException("message too long");
        }
        
        out.write((message.length >> BYTESHIFT) & BYTEMASK);
        out.write(message.length & BYTEMASK);
        out.write(message);
        out.flush();
    }
    
    @Override
    public byte[] nextMsg() throws IOException
    {
        int length = 0;
        try
        {
            // 该方法读取两个字节，并将它们作为big-endian整数进行解释，然后以int型整数返回它们的值
            length = in.readUnsignedShort();
        }
        catch (EOFException e)
        {
            return null;
        }
        //该方法处于阻塞状态，直到接收到足够的字节填充满指定的数组
        byte[] message = new byte[length];
        in.readFully(message);
        return message;
    }
    
}
