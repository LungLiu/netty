package the.flash.protocol.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import the.flash.protocol.Packet;

import static the.flash.protocol.command.Command.GROUP_MESSAGE_REQUEST;

/**
 * @Author:MH
 * @Date:Created in 10:48 2018/10/23
 */
@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {
    private String toGroupId;

    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message){
        this.toGroupId = toGroupId;
        this.message = message;
    }
    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
