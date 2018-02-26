package cn.huang.socket.messagevote;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 基于二进制的消息编解码
 * @author huangyejun
 *
 */
public class VoteMsgBinCoder implements VoteMsgCoder
{
    
    // manifest constants for encoding
    private static final int MIN_WIRE_LENGTH = 4;
    
    private static final int MAGIC = 0x5400;
    
    private static final int MAGIC_MASK = 0xfc00;
    
    private static final int MAGIC_SHIFT = 8;
    
    private static final int RESPONSE_FLAG = 0x0200;
    
    private static final int INQUIRE_FLAG = 0x0100;
    
    @Override
    public byte[] toWire(VoteMsg msg) throws IOException
    {
        ByteArrayOutputStream msgStream = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(msgStream);
        
        short magicAndFlags = MAGIC;
        if (msg.isInquiry())
        {
            magicAndFlags |= INQUIRE_FLAG;
        }
        if (msg.isResponse())
        {
            magicAndFlags |= RESPONSE_FLAG;
        }
        out.writeShort(magicAndFlags);
        // 写入候选人id
        out.writeShort(msg.getCandidateID());
        if (msg.isResponse())
        {
            out.writeLong(msg.getVoteCount());
        }
        out.flush();
        byte[] data = msgStream.toByteArray();
        return data;
    }
    
    @Override
    public VoteMsg fromWire(byte[] input) throws IOException
    {
        // sanity checks
        if (input.length < MIN_WIRE_LENGTH)
        {
            throw new IOException("Runt message");
        }
        ByteArrayInputStream bs = new ByteArrayInputStream(input);
        DataInputStream in = new DataInputStream(bs);
        int magic = in.readShort();
        if ((magic & MAGIC_MASK) != MAGIC)
        {
            throw new IOException("Bad Magic #: " + ((magic & MAGIC_MASK) >> MAGIC_SHIFT));
        }
        
        boolean resp = ((magic & RESPONSE_FLAG) != 0);
        boolean inq = ((magic & INQUIRE_FLAG) != 0);
        int candidateID = in.readShort();
        if (candidateID < 0 || candidateID > 1000)
        {
            throw new IOException("Bad candidate ID: " + candidateID);
        }
        
        long count = 0;
        if (resp)
        {
            count = in.readLong();
            if (count < 0)
            {
                throw new IOException("Bad vote count: " + count);
            }
        }
        return new VoteMsg(resp, inq, candidateID, count);
    }
    
}
