package jp.co.sakamoto.androidproject.presentation.view.helper;

import android.os.Handler;
import android.os.Message;

import java.util.Vector;

public abstract class PauseHandler extends Handler {
    private final Vector<Message> messageQueueBuffer = new Vector<>();

    private boolean paused;

    final public void resume() {
        paused = false;

        while (messageQueueBuffer.size() > 0) {
            final Message msg = messageQueueBuffer.elementAt(0);
            messageQueueBuffer.removeElementAt(0);
            sendMessage(msg);
        }
    }

    final public void pause() {
        paused = true;
    }

    protected abstract boolean storeMessage(Message message);
    protected abstract void processMessage(Message message);
    @Override
    final public void handleMessage(Message msg) {
        if (paused) {
            if (storeMessage(msg)) {
                Message msgCopy = new Message();
                msgCopy.copyFrom(msg);
                messageQueueBuffer.add(msgCopy);
            }
        } else {
            processMessage(msg);
        }
    }
}
