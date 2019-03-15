package socket.messagevote;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 基于文本的消息编解码
 * @author huangyejun
 *
 */
public class VoteMsgTextCoder implements VoteMsgCoder
{
    /*
     * Wire Format "VOTEPROTO" <"v" | "i"> [<RESPFLAG>] <CANDIDATE> [<VOTECNT>]
     * Charset is fixed by the wire format.
     */
    
    // manifest constants for encoding
    public static final String MAGIC = "Voting";
    
    public static final String VOTESTR = "v";
    
    public static final String INQSTR = "i";
    
    public static final String RESPONSESTR = "R";
    
    public static final String CHARSETNAME = "US-ASCII";
    
    public static final String DELIMITER = " ";
    
    public static final int MAX_WIRE_LENGTH = 2000;
    
    @Override
    public byte[] toWire(VoteMsg msg) throws IOException
    {
        String msgStr = MAGIC + DELIMITER + (msg.isInquiry() ? INQSTR : VOTESTR) + DELIMITER + (msg.isResponse() ? RESPONSESTR + DELIMITER : "") + Integer.toString(msg.getCandidateID()) + DELIMITER
                + Long.toString(msg.getVoteCount());
        byte[] data = msgStr.getBytes(CHARSETNAME);
        return data;
    }
    
    @Override
    public VoteMsg fromWire(byte[] input) throws IOException
    {
        ByteArrayInputStream msgStream = new ByteArrayInputStream(input);
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(new InputStreamReader(msgStream, CHARSETNAME));
        boolean isInquiry;
        boolean isResponse;
        int candidateID;
        long voteCount;
        String token;
        
        try
        {
            token = scanner.next();
            if (!token.equals(MAGIC))
            {
                throw new IOException("Bad magic string: " + token);
            }
            token = scanner.next();
            if (token.equals(VOTESTR))
            {
                isInquiry = false;
            }
            else if (!token.equals(INQSTR))
            {
                throw new IOException("Bad vote/inq indicator: " + token);
            }
            else
            {
                isInquiry = true;
            }
            
            token = scanner.next();
            if (token.equals(RESPONSESTR))
            {
                isResponse = true;
                token = scanner.next();
            }
            else
            {
                isResponse = false;
            }
            // Current token is candidateID
            // Note: isResponse now valid
            candidateID = Integer.parseInt(token);
            if (isResponse)
            {
                token = scanner.next();
                voteCount = Long.parseLong(token);
            }
            else
            {
                voteCount = 0;
            }
            
        }
        catch (Exception e)
        {
            throw new IOException("Parse error...");
        }
        return new VoteMsg(isResponse, isInquiry, candidateID, voteCount);
    }
    
}
