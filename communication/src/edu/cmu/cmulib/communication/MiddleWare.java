package edu.cmu.cmulib.communication;

/**
 * Created by amaliujia on 14-10-23.
 */
public interface MiddleWare {

    // when a slave returns computational result,
    // MiddleWare will be notified.
    public void msgReceived(int nodeID, CommonPacket packet);
}
