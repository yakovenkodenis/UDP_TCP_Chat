package persistence.models;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "ChatGroupMembers")
public class ChatGroupMembers implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "chat_group_id")
    private ChatGroup chatGroup;

    @ManyToOne
    @JoinColumn(name = "chat_user_id")
    private ChatUser chatUser;

    public ChatGroupMembers() {}

    public ChatGroupMembers(ChatGroup chatGroup, ChatUser chatUser) {
        this.chatGroup = chatGroup;
        this.chatUser = chatUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ChatGroup getChatGroup() {
        return chatGroup;
    }

    public void setChatGroup(ChatGroup chatGroup) {
        this.chatGroup = chatGroup;
    }

    public ChatUser getChatUser() {
        return chatUser;
    }

    public void setChatUser(ChatUser chatUser) {
        this.chatUser = chatUser;
    }

    @Override
    public String toString() {
        return "ChatGroupMembers{" +
                "id=" + id +
                ", chatGroup=" + chatGroup +
                ", chatUser=" + chatUser +
                '}';
    }
}
